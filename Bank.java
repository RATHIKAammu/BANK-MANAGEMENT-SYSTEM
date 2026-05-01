import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Bank {
    private Map<String, Account> accounts = new HashMap<>();
    private int accountCounter = 1001;

    private static final double SAVINGS_MIN = 500.0;
    private static final double CURRENT_MIN = 1000.0;

    // ── GENERATE ACCOUNT NUMBER ──
    private String generateAccountNumber() {
        return "RB" + (accountCounter++);
    }

    // ── CREATE ACCOUNT ──
    public void createAccount(Scanner sc) {
        System.out.println("\n🆕 CREATE NEW ACCOUNT");
        System.out.println("------------------------------------------");

        System.out.print("Full Name       : ");
        String name = sc.nextLine().trim();

        System.out.print("Phone Number    : ");
        String phone = sc.nextLine().trim();

        System.out.println("Account Type    : 1. Savings  2. Current");
        System.out.print("Choose (1/2)    : ");
        String typeChoice = sc.nextLine().trim();
        String type = typeChoice.equals("2") ? "Current" : "Savings";

        double minDeposit = type.equals("Savings") ? SAVINGS_MIN : CURRENT_MIN;
        System.out.printf("Initial Deposit : (Minimum ₹%.0f) ₹", minDeposit);

        double deposit;
        try {
            deposit = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount.");
            return;
        }

        if (deposit < minDeposit) {
            System.out.printf("❌ Minimum deposit for %s account is ₹%.0f%n", type, minDeposit);
            return;
        }

        String accNo = generateAccountNumber();
        Account account = new Account(accNo, name, type, phone, deposit);
        accounts.put(accNo, account);

        System.out.println("\n✅ Account Created Successfully!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  🏦 Account Number : " + accNo);
        System.out.println("  👤 Name           : " + name);
        System.out.println("  💳 Type           : " + type);
        System.out.printf ("  💰 Balance        : ₹%.2f%n", deposit);
        System.out.println("  📌 Please save your Account Number!");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // ── DEPOSIT ──
    public void deposit(Scanner sc) {
        System.out.println("\n💰 DEPOSIT MONEY");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;

        System.out.print("Amount to Deposit (₹): ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount."); return;
        }

        if (amount <= 0) { System.out.println("❌ Amount must be greater than 0."); return; }

        System.out.print("Remark (optional): ");
        String remark = sc.nextLine().trim();
        if (remark.isEmpty()) remark = "Cash Deposit";

        acc.deposit(amount, remark);
        System.out.printf("%n✅ ₹%.2f deposited successfully!%n", amount);
        System.out.printf("   Current Balance: ₹%.2f%n", acc.getBalance());
    }

    // ── WITHDRAW ──
    public void withdraw(Scanner sc) {
        System.out.println("\n💸 WITHDRAW MONEY");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;

        System.out.print("Amount to Withdraw (₹): ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount."); return;
        }

        if (amount <= 0) { System.out.println("❌ Amount must be greater than 0."); return; }

        System.out.print("Remark (optional): ");
        String remark = sc.nextLine().trim();
        if (remark.isEmpty()) remark = "Cash Withdrawal";

        boolean success = acc.withdraw(amount, remark);
        if (success) {
            System.out.printf("%n✅ ₹%.2f withdrawn successfully!%n", amount);
            System.out.printf("   Remaining Balance: ₹%.2f%n", acc.getBalance());
        } else {
            double minBal = acc.getAccountType().equals("Savings") ? SAVINGS_MIN : CURRENT_MIN;
            System.out.printf("❌ Insufficient balance! Minimum balance of ₹%.0f must be maintained.%n", minBal);
        }
    }

    // ── TRANSFER ──
    public void transfer(Scanner sc) {
        System.out.println("\n🔄 TRANSFER MONEY");

        System.out.print("From Account Number: ");
        String fromAcc = sc.nextLine().trim().toUpperCase();
        Account from = accounts.get(fromAcc);
        if (from == null || !from.isActive()) {
            System.out.println("❌ Sender account not found or inactive."); return;
        }

        System.out.print("To Account Number  : ");
        String toAcc = sc.nextLine().trim().toUpperCase();
        Account to = accounts.get(toAcc);
        if (to == null || !to.isActive()) {
            System.out.println("❌ Recipient account not found or inactive."); return;
        }

        if (fromAcc.equals(toAcc)) {
            System.out.println("❌ Cannot transfer to the same account."); return;
        }

        System.out.print("Amount to Transfer (₹): ");
        double amount;
        try {
            amount = Double.parseDouble(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("❌ Invalid amount."); return;
        }

        if (amount <= 0) { System.out.println("❌ Amount must be greater than 0."); return; }

        boolean success = from.withdraw(amount, "Transfer to " + toAcc);
        if (success) {
            to.deposit(amount, "Transfer from " + fromAcc);
            System.out.printf("%n✅ ₹%.2f transferred successfully!%n", amount);
            System.out.printf("   From: %s (%s) → New Balance: ₹%.2f%n",
                    fromAcc, from.getHolderName(), from.getBalance());
            System.out.printf("   To  : %s (%s) → New Balance: ₹%.2f%n",
                    toAcc, to.getHolderName(), to.getBalance());
        } else {
            System.out.println("❌ Transfer failed! Insufficient balance.");
        }
    }

    // ── CHECK BALANCE ──
    public void checkBalance(Scanner sc) {
        System.out.println("\n💳 CHECK BALANCE");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Account : " + acc.getAccountNumber());
        System.out.println("  Name    : " + acc.getHolderName());
        System.out.printf ("  Balance : ₹%.2f%n", acc.getBalance());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // ── MINI STATEMENT ──
    public void miniStatement(Scanner sc) {
        System.out.println("\n📄 MINI STATEMENT (Last 5 Transactions)");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;

        List<Transaction> txns = acc.getTransactions();
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Account: " + acc.getAccountNumber() + "  |  Name: " + acc.getHolderName());
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");

        int start = Math.max(0, txns.size() - 5);
        for (int i = start; i < txns.size(); i++) {
            System.out.println(txns.get(i));
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("  Current Balance: ₹%.2f%n", acc.getBalance());
    }

    // ── FULL ACCOUNT DETAILS ──
    public void fullAccountDetails(Scanner sc) {
        System.out.println("\n📋 FULL ACCOUNT DETAILS");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println(acc);
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  All Transactions (" + acc.getTransactions().size() + "):");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        for (Transaction t : acc.getTransactions()) {
            System.out.println(t);
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    // ── VIEW ALL ACCOUNTS ──
    public void viewAllAccounts() {
        System.out.println("\n👥 ALL ACCOUNTS");
        if (accounts.isEmpty()) { System.out.println("No accounts found."); return; }

        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.printf("  %-10s  %-20s  %-10s  %12s  %s%n",
                "Acc No", "Name", "Type", "Balance", "Status");
        System.out.println("  " + "─".repeat(68));

        for (Account acc : accounts.values()) {
            System.out.printf("  %-10s  %-20s  %-10s  ₹%10.2f  %s%n",
                    acc.getAccountNumber(), acc.getHolderName(),
                    acc.getAccountType(), acc.getBalance(),
                    acc.isActive() ? "✅ Active" : "❌ Closed");
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("  Total Accounts: " + accounts.size());
    }

    // ── CLOSE ACCOUNT ──
    public void closeAccount(Scanner sc) {
        System.out.println("\n🚫 CLOSE ACCOUNT");
        Account acc = getActiveAccount(sc);
        if (acc == null) return;

        System.out.printf("  Are you sure you want to close account %s (%s)? (yes/no): ",
                acc.getAccountNumber(), acc.getHolderName());
        String confirm = sc.nextLine().trim().toLowerCase();

        if (confirm.equals("yes")) {
            acc.closeAccount();
            System.out.println("✅ Account closed successfully.");
            System.out.printf("   Final Balance: ₹%.2f (please collect from branch)%n", acc.getBalance());
        } else {
            System.out.println("❌ Account closure cancelled.");
        }
    }

    // ── HELPER: Get active account ──
    private Account getActiveAccount(Scanner sc) {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine().trim().toUpperCase();
        Account acc = accounts.get(accNo);
        if (acc == null) {
            System.out.println("❌ Account not found.");
            return null;
        }
        if (!acc.isActive()) {
            System.out.println("❌ This account is closed.");
            return null;
        }
        return acc;
    }
}
