public class TopManager extends Worker {

    private Company company;

    public TopManager(int fixPartOfSalary) {
        super(fixPartOfSalary);
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    @Override
    public int getMonthSalary() {
        return company.getIncome() > 10_000_000 ?
                fixPartOfSalary + fixPartOfSalary / 100 * 150 : fixPartOfSalary;
    }
}
