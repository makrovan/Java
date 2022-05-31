import org.redisson.Redisson;
import org.redisson.api.RKeys;
import org.redisson.api.RScoredSortedSet;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisConnectionException;
import org.redisson.config.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.out;

public class RedisStorage implements Runnable
{
    // Объект для работы с Redis
    private RedissonClient redisson;

    // Объект для работы с Sorted Set'ом
    private RScoredSortedSet<String> users;

    private final static String KEY = "USERS";
    private final static Double userCount = 20D;
    private volatile boolean isRunning;
    private static final int SLEEP = 1000;

    public RedisStorage() {
        isRunning = true;
        init();
    }

    private void init() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://localhost:55000");
        config.useSingleServer().setClientName("default");
        config.useSingleServer().setPassword("redispw");
        try {
            redisson = Redisson.create(config);
        } catch (RedisConnectionException Exc) {
            out.println("Не удалось подключиться к Redis");
            out.println(Exc.getMessage());
        }
        users = redisson.getScoredSortedSet(KEY);

        for (int i = 0; i < userCount; i++){
            users.add(i, "User " + (i + 1));
        }
    }

    public void stop() {
        isRunning = false;
    }

    @Override
    public void run() {
        while (isRunning){
            int user_id = new Random().nextInt(userCount.intValue());
            //System.out.println(user_id);
            for (int i = 0; i < userCount; i++){

                //на i-ом шаге случайный пользователь оплачивает услугу
                if (user_id == i) {
                    String paidUser = users.random();
                    System.out.println(paidUser + " paid for the service");
                    System.out.println("Now " + paidUser + " is displaying");
                    try {
                        Thread.sleep(SLEEP * 2);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }

                    //если отобразили пользователя, очередь которого уже была в этом цикле:
                    double score = (users.getScore(paidUser) < userCount) ? users.getScore(paidUser) :
                            users.getScore(paidUser) % userCount;
                    if (score < user_id) {
                        i--;
                        user_id = -1;
                    } else {
                        users.addScore(paidUser, userCount);
                    }
                    continue;
                }

                String currentUser = users.first();
                Double currentScore = users.getScore(currentUser);
                users.pollFirst();
                users.add(currentScore + userCount, currentUser);
                System.out.println("Now " + currentUser + " is displaying");
                try {
                    Thread.sleep(SLEEP / 2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        close();
    }

    private void close(){
        RKeys rKeys = redisson.getKeys();
        rKeys.delete(KEY);
        redisson.shutdown();
    }
}
