import java.util.*;

public class Company {

    private final static int BASESALARY = 80_000;

    public enum Type {OPERATOR, TOPMANAGER, MANAGER}
    public ArrayList<Employee> employees;
    private int income;

    public Company() {
        employees = new ArrayList<>();
    }

    public void hire(int fixPartOfSalary, Type type){
        switch (type) {
            case MANAGER -> employees.add(new Manager(fixPartOfSalary));
            case OPERATOR -> employees.add(new Operator(fixPartOfSalary));
            case TOPMANAGER -> {
                TopManager topManager = new TopManager(fixPartOfSalary);
                topManager.setCompany(this);
                employees.add(topManager);
            }
        }
    }

    public void hire(Type type){
        hire(BASESALARY, type);
    }

    public void hireAll(int count, Type type) {
        for (int i = 0; i < count; i++){
            hire(type);
        }
    }

    public void fire(Type type) {
        String className = "";
        switch (type) {
            case OPERATOR -> className = "Operator";
            case MANAGER -> className = "Manager";
            case TOPMANAGER -> className = "TopManager";
        }

        for (Employee employee : employees){
            if (employee.getClass().getName().equals(className)) { //либо instanceof
                employees.remove(employee);
                break;
            }
        }
    }

    //count - сколько нужно уволить сотрудников
    public void fire(int count) {
        for (int i = 0; i < count; i++) {
            int typeNumber = (int) ((Math.random() * 3) + 1);
            Type type = switch (typeNumber) {
                case 3 -> Type.TOPMANAGER;
                case 2 -> Type.MANAGER;
                default -> Type.OPERATOR;
            };
            fire(type);
        }
    }

    /*//возвращаем отсортированный набор
    Set<Worker> sortList() {
        Set<Worker> workerSet = new TreeSet<>();
        for (Employee employee : employees){
            workerSet.add((Worker) employee);
        }
        //ArrayList workerList = new ArrayList<>(workerSet);
        //Collections.sort(workerList);
        return workerSet;
    }*/

    //сотрудники с самыми высокими заработными платами, отсортированный по убыванию
    List<Employee> getTopSalaryStaff(int count) {

        //TreeSet<Employee> employeeSet = ;
        ArrayList<Employee> workers = new ArrayList<>(new TreeSet<>(employees));

        count = Math.min(count, workers.size());
        count = Math.max(count, 0);

        ArrayList<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employeeList.add(workers.get(workers.size() - 1 - i));
        }
        return employeeList;
    }

    //сотрудники с самыми низкими заработными платами, отсортированный по возрастанию
    List<Employee> getLowestSalaryStaff(int count){

        //ArrayList<Worker> workers = new ArrayList<>(sortList());
        ArrayList<Employee> workers = new ArrayList<>(new TreeSet<>(employees));

        count = Math.min(count, workers.size());
        count = Math.max(count, 0);

        ArrayList<Employee> employeeList = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            employeeList.add(workers.get(i));
        }
        return employeeList;
    }

    public void setIncome (int income) {
        this.income = income;
    }

    public int getIncome() {
        return income;
    }
}
