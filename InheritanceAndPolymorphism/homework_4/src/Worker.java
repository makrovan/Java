public abstract class Worker implements Employee {
    protected int fixPartOfSalary;

    public Worker(int fixPartOfSalary) {
        this.fixPartOfSalary = fixPartOfSalary;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + ": " + getMonthSalary() + " руб.";
    }
}
