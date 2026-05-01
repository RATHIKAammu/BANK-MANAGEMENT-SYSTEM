# 🏦 Bank Management System

A console-based Java application simulating core banking operations. Built using Object-Oriented Programming principles with multiple classes, real-time transaction tracking, and balance management rules.

## 📌 Features

-  Create Savings or Current accounts with auto-generated account numbers
-  Deposit money with custom remarks
-  Withdraw money with minimum balance enforcement
-  Transfer money between two accounts
-  Check account balance instantly
-  Mini Statement — last 5 transactions with date & time
-  Full account details with complete transaction history
-  View all accounts in a formatted table
-  Close account with confirmation
-  Auto-timestamps on every transaction using `LocalDateTime`

## 🛠️ Technologies Used

- **Language:** Java (JDK 11+)
- **IDE:** Eclipse
- **Concepts:** OOP, Collections (HashMap, ArrayList), Exception Handling, Java DateTime API

## 📂 Project Structure

```
bank-management-system/
│
├── src/
│   ├── Main.java           # Entry point, menu-driven interface
│   ├── Bank.java           # Core banking operations (CRUD + transactions)
│   ├── Account.java        # Account model with deposit/withdraw logic
│   └── Transaction.java    # Transaction model with timestamp
│
└── README.md
```

## ▶️ How to Run

### In Eclipse:
1. Open Eclipse → `File` → `New` → `Java Project`
2. Name it `BankManagementSystem`
3. Right-click `src` → Import → paste all 4 `.java` files
4. Right-click `Main.java` → `Run As` → `Java Application`

### In Terminal:
```bash
cd src
javac *.java
java Main
```

## 💻 Sample Output

```
╔══════════════════════════════════════════╗
║      🏦 Bank Management System           ║
║         Welcome to RathikaBank           ║
╚══════════════════════════════════════════╝

✅ Account Created Successfully!
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  🏦 Account Number : RB1001
  👤 Name           : Rathika S
  💳 Type           : Savings
  💰 Balance        : ₹5000.00

📄 MINI STATEMENT (Last 5 Transactions)
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  ➕  CREDIT    ₹   5000.00  Bal: ₹   5000.00  Account opened          01-08-2024 10:30:00
  ➕  CREDIT    ₹   2000.00  Bal: ₹   7000.00  Cash Deposit            01-08-2024 10:35:00
  ➖  DEBIT     ₹    500.00  Bal: ₹   6500.00  Cash Withdrawal         01-08-2024 10:40:00
  🔄  DEBIT     ₹   1000.00  Bal: ₹   5500.00  Transfer to RB1002      01-08-2024 10:45:00
━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━
  Current Balance: ₹5500.00
```

## 🎯 Concepts Demonstrated

| Concept | Usage |
| Object-Oriented Programming | `Bank`, `Account`, `Transaction` classes |
| Encapsulation | Private fields, public getters/setters |
| Collections Framework | `HashMap` for accounts, `ArrayList` for transactions |
| Exception Handling | Try-catch for invalid numeric inputs |
| Java DateTime API | `LocalDateTime` for auto-timestamping transactions |
| Business Logic | Minimum balance rules for Savings (₹500) & Current (₹1000) |
| Switch Expressions | Java 14+ arrow switch for clean menu handling |
| String Formatting | `printf` for aligned tabular output |

##  Author

Rathika S — B.E. Electronics and Communication Engineering  
KGiSL Institute of Technology, Coimbatore  
GitHub: github.com/rathikaseenivas
