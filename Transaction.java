import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;       // CREDIT / DEBIT
    private double amount;
    private String remark;
    private double balanceAfter;
    private String dateTime;

    private static final DateTimeFormatter FMT =
            DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    public Transaction(String type, double amount, String remark, double balanceAfter) {
        this.type         = type;
        this.amount       = amount;
        this.remark       = remark;
        this.balanceAfter = balanceAfter;
        this.dateTime     = LocalDateTime.now().format(FMT);
    }

    public String getType()        { return type; }
    public double getAmount()      { return amount; }
    public String getRemark()      { return remark; }
    public double getBalanceAfter(){ return balanceAfter; }
    public String getDateTime()    { return dateTime; }

    @Override
    public String toString() {
        String symbol = type.equals("CREDIT") ? "➕" : "➖";
        return String.format("  %s  %-8s  ₹%10.2f  Bal: ₹%10.2f  %-30s  %s",
                symbol, type, amount, balanceAfter, remark, dateTime);
    }
}
