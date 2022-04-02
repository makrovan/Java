import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class Main {

    private static final String STAFF_TXT = "data/staff.txt";

    public static void main(String[] args) {
        List<Employee> staff = Employee.loadStaffFromFile(STAFF_TXT);
        Employee employeeMaxSalary = findEmployeeWithHighestSalary(staff, 2017);
        System.out.println(employeeMaxSalary);
    }

    public static Employee findEmployeeWithHighestSalary(List<Employee> staff, int year) {
        // Метод должен вернуть сотрудника с максимальной зарплатой среди тех,
        // кто пришёл в году, указанном в переменной year
        /*Stream<Employee> stream = staff.stream();
        //stream.forEach(System.out::println);
        stream = staff.stream().filter(employee -> {
            Calendar cal = Calendar.getInstance();
            cal.setTime(employee.getWorkStart());
            return cal.get(Calendar.YEAR) == year;
        });
        //stream.forEach(System.out::println);
        Optional<Employee> optional = stream.max(Comparator.comparing(Employee::getSalary));
        *//*Optional<Employee> optional = stream.max(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });*//*
        //optional.ifPresent(System.out::println);
        //stream.max(Comparator.comparing(Employee::getSalary));
        Employee employee = optional.get();
        return employee;*/
        return staff.stream().filter(e -> e.getWorkStart().getYear() == year - 1900).
                max(Comparator.comparing(Employee::getSalary)).get();
    }
}