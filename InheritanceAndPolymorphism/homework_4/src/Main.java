import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Company company = new Company();
        company.setIncome(10_000_001);
        company.hireAll(180, Company.Type.OPERATOR);
        company.hireAll(80, Company.Type.MANAGER);
        company.hireAll(10, Company.Type.TOPMANAGER);

        printResult((ArrayList<Employee>) company.getTopSalaryStaff(15),
                (ArrayList<Employee>) company.getLowestSalaryStaff(30));

        company.fire(company.employees.size() / 100 * 50);
        System.out.println("\nУволили 50%: ");

        printResult((ArrayList<Employee>) company.getTopSalaryStaff(12),
                (ArrayList<Employee>) company.getLowestSalaryStaff(5));

    }

    public static void printResult(ArrayList<Employee> employeesTop, ArrayList<Employee> employeesDown) {

        System.out.println("\nТопзарплаты: ");
        for (Employee employee : employeesTop) {
            System.out.println(employee);
        }

        System.out.println("\nСамые низкие зарплаты: ");
        for (Employee employee : employeesDown) {
            System.out.println(employee);
        }
    }
}