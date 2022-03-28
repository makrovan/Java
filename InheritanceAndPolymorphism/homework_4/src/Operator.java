public class Operator extends Worker {
    public Operator(int fixPartOfSalary) {
        super(fixPartOfSalary);
    }

    @Override
    public int getMonthSalary() {
        return fixPartOfSalary;
    }
}
