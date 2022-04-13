import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class Movements {
    private final ArrayList<FinanceRecord> records = new ArrayList<>();

    public Movements(String pathMovementsCsv) {
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(pathMovementsCsv));
            lines.remove(0);
            lines.forEach(this::parser);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void parser (String line){
        try {
            records.add(FinanceRecord.parser(line));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public double getExpenseSum() {
        return records.stream().map(FinanceRecord::getExpense).reduce(Float::sum).orElse(0.0F);
    }

    public double getIncomeSum() {
        return records.stream().map(FinanceRecord::getIncome).reduce(Float::sum).orElse(0.0F);
    }

    public List<String> getExpenseSumByOrganisation() {
        HashSet <String> names = new HashSet<>(records.stream().
                map(FinanceRecord::getOrganisationName).collect(Collectors.toList()));
        ArrayList <String> result = new ArrayList<>();
        for (String name : names) {
            Float sum = records.stream().
                    filter(financeRecord -> financeRecord.getOperationDescription().getName().equals(name)).
                    map(FinanceRecord::getExpense).
                    reduce(Float::sum).orElse(0.0F);
            DecimalFormat df = new DecimalFormat("###,###.##");
            result.add(name + "\t" + df.format(sum) + " руб.");
        }
        return result;
    }
}
