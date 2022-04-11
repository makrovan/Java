import java.text.DecimalFormat;

public class Main {
    private static final String csvFile = "/Users/anton/IdeaProjects/java_basics/FilesAndNetwork/" +
            "homework_3/src/test/resources/movementList.csv";

    public static void main(String[] args) {

        Movements movements = new Movements(csvFile);
        DecimalFormat df = new DecimalFormat("###,###.##");
        System.out.println("Сумма расходов: " + df.format(movements.getExpenseSum()) + " руб.");
        System.out.println("Сумма доходов: " + df.format(movements.getIncomeSum()) + " руб.");
        System.out.println("Суммы расходов по организациям:");
        movements.getExpenseSumByOrganisation().forEach(System.out::println);
    }
}
