public class Manager extends Worker {
    private int earnings;

    public void setEarnings() {
        earnings = (int) (Math.random() * (140_000 - 115_000)) + 115_000;
    }

    public Manager(int fixPartOfSalary) {
        super(fixPartOfSalary);
        setEarnings();
    }

    @Override
    public int getMonthSalary() {
        return fixPartOfSalary + earnings / 100 * 5;
    }
}
