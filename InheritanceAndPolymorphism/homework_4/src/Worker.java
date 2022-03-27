public abstract class Worker implements Employee, Comparable {
    protected int fixPartOfSalary;

    public Worker(int fixPartOfSalary) {
        this.fixPartOfSalary = fixPartOfSalary;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + getMonthSalary() + " руб.";
    }

    @Override
    public int compareTo(Object o) {
        Worker operator = (Worker) o;
        return Integer.compare(getMonthSalary(), operator.getMonthSalary());
    }
}
