import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)
    {
        RedisStorage redis = new RedisStorage();

        new Thread(redis).start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        redis.stop();
    }
}
