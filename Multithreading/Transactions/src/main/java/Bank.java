import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
@Data
public class Bank {

    private Map<String, Account> accounts = new HashMap<>();
    private final Random random = new Random();

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
        throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами. Если сумма транзакции > 50000,
     * то после совершения транзакции, она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка счетов (как – на ваше
     * усмотрение)
     */
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Account fromAccount = accounts.get(fromAccountNum);
        Account toAccount = accounts.get(toAccountNum);
        if ((fromAccount.getMoney() >= amount) && !fromAccount.isBlockSign() && !toAccount.isBlockSign())
        {
            synchronized (Bank.class){
                fromAccount.takeMoney(amount);
                toAccount.addMoney(amount);
                System.out.println("transfer from " + fromAccountNum + " to " + toAccountNum + " done!");
            }
            if (amount > 50_000) {
                if (isFraud(fromAccountNum, toAccountNum,amount)){
                    fromAccount.setBlockSign(true);
                    toAccount.setBlockSign(true);
                    System.out.println("Client " + fromAccountNum + " blocked!");
                    System.out.println("Client " + toAccountNum + " blocked!");
                    System.out.println("Sum of transaction is " + amount);
                }
            }
        }
        if (fromAccount.isBlockSign()) {
            System.out.println("Client " + fromAccountNum + " already blocked!");
        }
        if (toAccount.isBlockSign()) {
            System.out.println("Client " + toAccountNum + " already blocked!");
        }
        /*
         while(isNotBlocked) и у отрправителя и у получателя ...
         перевод денег делаем внутри synchronized-блока, внутри которого проверяем, что у отправителя
         денег достаточно, зачисляем нужную сумму получателю.
         После этого этого, за пределами synchronized-блока проверяем, что сумма
         менее 50_000, иначе вызываем isFraud и по его результатам можем установить isNotBlocked у обоих счетов в false.
         Проверка  while(isNotBlocked) делается в самом начале метода.

         Можно считать, что таких транзакций не более 5% от всех. - как в тестах реализовать,
         что переводов на сумму более 50_000 будет не более 5%.
        * */

    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     *
     * Проверяйте сумму на банковских счетах до запуска транзакций и после завершения —
     * сумма в банке не должна измениться.
     *
     */
    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        return accounts.values().stream().mapToLong(a -> a.getMoney()).sum();
    }
}
