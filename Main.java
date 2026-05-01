import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Bank bank = new Bank();

        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║      🏦 Bank Management System           ║");
        System.out.println("║         Welcome to RathikaBank           ║");
        System.out.println("╚══════════════════════════════════════════╝");

        while (true) {
            System.out.println("\n📋 MAIN MENU");
            System.out.println("1.  Create New Account");
            System.out.println("2.  Deposit Money");
            System.out.println("3.  Withdraw Money");
            System.out.println("4.  Transfer Money");
            System.out.println("5.  Check Balance");
            System.out.println("6.  Mini Statement (Last 5 Transactions)");
            System.out.println("7.  Full Account Details");
            System.out.println("8.  View All Accounts");
            System.out.println("9.  Close Account");
            System.out.println("10. Exit");
            System.out.println("------------------------------------------");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1  -> bank.createAccount(sc);
                case 2  -> bank.deposit(sc);
                case 3  -> bank.withdraw(sc);
                case 4  -> bank.transfer(sc);
                case 5  -> bank.checkBalance(sc);
                case 6  -> bank.miniStatement(sc);
                case 7  -> bank.fullAccountDetails(sc);
                case 8  -> bank.viewAllAccounts();
                case 9  -> bank.closeAccount(sc);
                case 10 -> {
                    System.out.println("\n✅ Thank you for using RathikaBank. Goodbye!");
                    sc.close();
                    System.exit(0);
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }
}
