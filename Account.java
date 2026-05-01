import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountNumber;
    private String holderName;
    private String accountType;   // Savings / Current
    private String phone;
    private double balance;
    private boolean isActive;
    private List<Transaction> transactions;

    public Account(String accountNumber, String holderName, String accountType,
                   String phone, double initialDeposit) {
        this.accountNumber  = accountNumber;
        this.holderName     = holderName;
        this.accountType    = accountType;
        this.phone          = phone;
        this.balance        = initialDeposit;
        this.isActive       = true;
        this.transactions   = new ArrayList<>();

        // Record opening deposit
        transactions.add(new Transaction("CREDIT", initialDeposit,
                "Account opened with initial deposit", balance));
    }

    // ── Getters ──
    public String getAccountNumber() { return accountNumber; }
    public String getHolderName()    { return holderName; }
    public String getAccountType()   { return accountType; }
    public String getPhone()         { return phone; }
    public double getBalance()       { return balance; }
    public boolean isActive()        { return isActive; }
    public List<Transaction> getTransactions() { return transactions; }

    // ── Operations ──
    public void deposit(double amount, String remark) {
        balance += amount;
        transactions.add(new Transaction("CREDIT", amount, remark, balance));
    }

    public boolean withdraw(double amount, String remark) {
        double minBalance = accountType.equalsIgnoreCase("Savings") ? 500.0 : 1000.0;
        if (balance - amount < minBalance) return false;
        balance -= amount;
        transactions.add(new Transaction("DEBIT", amount, remark, balance));
        return true;
    }

    public void closeAccount() {
        this.isActive = false;
    }

    @Override
    public String toString() {
        return String.format(
            "  Account No : %s%n  Name       : %s%n  Type       : %s%n" +
            "  Phone      : %s%n  Balance    : ₹%.2f%n  Status     : %s",
            accountNumber, holderName, accountType,
            phone, balance, isActive ? "✅ Active" : "❌ Closed"
        );
    }
}
