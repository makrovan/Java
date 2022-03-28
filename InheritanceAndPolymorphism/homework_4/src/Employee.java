public interface Employee extends Comparable {
    int getMonthSalary();

    @Override
    default int compareTo(Object o) {
        Worker operator = (Worker) o;
        return Integer.compare(getMonthSalary(), operator.getMonthSalary());
    }
}
