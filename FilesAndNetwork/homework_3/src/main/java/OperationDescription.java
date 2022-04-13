import lombok.AllArgsConstructor;
import lombok.Data;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

@Data
@AllArgsConstructor
public class OperationDescription {
    //548673++++++1028    809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW          31.05.17 31.05.17 1500.00       RUR MCC6536
    //548673++++++1028      210102\643\MOSKVA\Alfa Iss                      10.05.17 09.05.17     40000.00  RUR MCC6011
    //548673++++++1028    11436154\RUS\MOSCOW\16 LVA\YANDEX TAXI            05.05.17 02.05.17        89.00  RUR MCC4121
    //548673++++++1028    \GBR\Bicester\2 MI\FSPRG UK                       22.03.17 20.03.17       953.44  RUR MCC5818
    private String someString1;
    private String name;
    private Date date1;
    private Date date2;
    private Float summa;
    private String currency;
    private String someString2;

    private static String dateFormat = "dd.MM.yy";

    private static String nameTransformation(String initial){
        //10788553\RUS\MOSCOW\42 SHC\L ETOILE -> RUSMOSCOW42 SHCL ETOILE
        //26083215\RUS\MOSKVA\56  SH\LOVE REPUBLIC -> RUSMOSKVA56  SHLOVE REPUBLIC
        //10502864\RUS\PUSHKINO\105\ZOOMAGAZIN 4 -> RUSPUSHKINO105ZOOMAGAZIN 4
        //809216  /RU/CARD2CARD ALFA_MOBILE>MOSCOW -> RUCARD2CARD ALFA_MOBILE>MOSCOW
        //\GBR\Bicester\2 MI\FSPRG UK -> GBRBicester2 MIFSPRG UK
        //\LUX\aws amazon co\AWS EMEA -> LUXaws amazon coAWS EMEA
        //210102\643\MOSKVA\Alfa Iss -> 643MOSKVAAlfa Iss
        //11436154\RUS\MOSCOW\16 LVA\YANDEX TAXI -> RUSMOSCOW16 LVAYANDEX TAXI
        String separator = initial.indexOf('\\') >= 0 ? "\\" : "/";
        String result = initial.trim();
        result = result.substring(result.indexOf(separator) + 1);
        separator = separator.equals("\\") ? "\\\\" : separator;
        result = result.replaceAll(separator, "");
        return result;
    }
    public static OperationDescription parser(String incomeString) throws ParseException {
        //удаление фразы "(Apple Pay-7666)" - это конечно "костыль", но Apple Pay в России не работает же уже...
        String[] arg = incomeString.replace("(Apple Pay-7666)", "").replaceAll("\\s+", " ").split(" ");
        assert arg.length > 5;

        String someString1 = arg[0].trim();
        String someString2 = arg[arg.length - 1].trim();
        String currency = arg[arg.length - 2].trim();
        Float summa = Float.parseFloat(arg[arg.length - 3].trim());
        Date date2 = (new SimpleDateFormat(dateFormat)).parse(arg[arg.length - 4].trim());
        Date date1 = (new SimpleDateFormat(dateFormat)).parse(arg[arg.length - 5].trim());
        String[] arg_1 = Arrays.copyOfRange(arg, 1,arg.length - 5);
        String name = (arg_1.length > 1) ? nameTransformation(String.join(" ", arg_1)) :
                nameTransformation(arg_1[0]);
        return new OperationDescription(someString1, name, date1, date2, summa, currency, someString2);
    }
}
