import lombok.AllArgsConstructor;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Data
@AllArgsConstructor
public class FinanceRecord {
    //Тип счёта,Номер счета,Валюта,Дата операции,Референс проводки,Описание операции,Приход,Расход
    //Текущий счёт,40817813206170024534,RUR,31.05.17,CRD_1U34U7,,1500,0
    public enum CheckType {CURRENT}
    private CheckType checkType;    //Текущий счёт
    private String checkNumber;     //40817813206170024534
    public enum Currency {RUB}
    private Currency currency;      //RUR
    private static String dateFormat = "dd.MM.yy";
    private Date operationDate;   //31.05.17
    private String wiringReference;     //CRD_1U34U7
    private OperationDescription operationDescription;
    //548673++++++1028    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          31.05.17 31.05.17 1500.00       RUR MCC6536
    //548673++++++1028      210102\643\MOSKVA\Alfa Iss                      10.05.17 09.05.17     40000.00  RUR MCC6011
    //548673++++++1028    11436154\RUS\MOSCOW\16 LVA\YANDEX TAXI            05.05.17 02.05.17        89.00  RUR MCC4121
    //548673++++++1028    \GBR\Bicester\2 MI\FSPRG UK                       22.03.17 20.03.17       953.44  RUR MCC5818
    private Float income;           //1500 or "1500,5"
    private Float expense;          //0 or "300,03"

    private static String correctRecord (String incomeString) {
        int index = 0;
        while ((index = incomeString.indexOf("\"", index)) != -1) {
            String replacedString = incomeString.substring(index, incomeString.indexOf("\"", index + 1) + 1).
                    replaceAll(",", ".");
            replacedString = replacedString.substring(1, replacedString.length() - 1);
            incomeString = incomeString.replace(incomeString.substring(index,
                            incomeString.indexOf("\"", index + 1) + 1), replacedString);
        }
        return incomeString;
    }

    public static FinanceRecord parser(String incomeString) throws ParseException {
        incomeString = correctRecord(incomeString);
        String[] arg = incomeString.split(",");
        assert arg.length == 8;
        //Arrays.stream(arg).forEach(String::trim);

        CheckType checkType;
        switch (arg[0]) {
            case "Текущий счёт" -> checkType = CheckType.CURRENT;
            default -> throw new IllegalStateException("Unexpected value: " + arg[0]);
        }
       String checkNumber = arg[1];
        Currency currency;
        switch (arg[2]) {
            case "RUR" -> currency = Currency.RUB;
            default -> throw new IllegalStateException("Unexpected value: " + arg[2]);
        }
        Date operationDate = (new SimpleDateFormat(dateFormat)).parse(arg[3]);
        String wiringReference = arg[4];
        OperationDescription operationDescription = OperationDescription.parser(arg[5]);
        Float income = Float.parseFloat(arg[6]);
        Float expense = Float.parseFloat(arg[7]);
        return new FinanceRecord(checkType,
                checkNumber,
                currency,
                operationDate,
                wiringReference,
                operationDescription,
                income,
                expense);
    }

    public static String getOrganisationName(FinanceRecord financeRecord) {
        return financeRecord.getOperationDescription().getName();
    }
}
