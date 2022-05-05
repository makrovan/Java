import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Random;

public class BankTest extends TestCase
{
    private final long sumAllAccounts = 50_000 * 1000;
    private Bank bank = new Bank();

    @Override
    protected void setUp() throws Exception {
        do
        {
            String accNumber = randomStringGenerator(10);
            Account account = new Account(50_000, accNumber, false);
            bank.getAccounts().put(accNumber, account);
        } while (bank.getAccounts().size() < 1000);
        super.setUp();
    }

    private static String randomStringGenerator(int targetStringLength)
    {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'
        Random random = new Random();

        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }

    public void testTransactionsInMultiThreads()
    {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            threads.add(new Thread(() -> startNewTransactionThread(bank)));
        }
        threads.forEach(Thread::start);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        threads.forEach(Thread::stop);

        long actual = bank.getSumAllAccounts();
        long expected = sumAllAccounts;
        assertEquals(expected, actual);
    }

    private void startNewTransactionThread(Bank bank){
        while(true)
        {
            Random random = new Random();
            //случайный размер транзакции, в которой транзакции более 50_000 составляют менее 50 %
            int transactionSizeRandom = random.nextInt(52_500 - 1) + 1;
            Object[] keys = bank.getAccounts().keySet().toArray();
            int randomClient1 = random.nextInt(1000);
            int randomClient2 = random.nextInt(1000);
            try {
                bank.transfer((String) keys[randomClient1], (String) keys[randomClient2], transactionSizeRandom);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
