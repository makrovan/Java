import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Account
{

    private long money;
    private String accNumber;
    private volatile boolean blockSign; //признак того, что счет заблокирован

    public void addMoney(long income){
        money += income;
    }

    public void takeMoney(long expense){
        money -= expense;
    }
}
