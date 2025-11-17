import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private double savingsGoal;
    private final List<String> transactionHistory;
    private String TRANSACTION_TYPE;
    private String transactionItem;


    public BankAccount(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        TRANSACTION_TYPE = "DEPOSIT";
        transactionItem = TRANSACTION_TYPE + ": " + amount;

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }

        balance += amount;
        addToTransactionHistory(transactionItem);
    }

    public boolean withdraw(double amount) {
        TRANSACTION_TYPE = "WITHDRAW";
        transactionItem = TRANSACTION_TYPE + ": " + amount;

        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance - amount < 0) {
            return false;
        }

        balance -= amount;
        addToTransactionHistory(transactionItem);
        return true;
    }

    public void addToTransactionHistory(String transactionItem) {
        transactionHistory.add(transactionHistory.size(), transactionItem);
    }

    public boolean getTransactionHistory() {
        return !transactionHistory.isEmpty();
    }

    public void printTransactionHistory() {
        for(String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public double calculateGoalProgress() {
        double goalProgress;
        // double savingsGoal = getSavingsGoal();

        goalProgress = (balance / savingsGoal) * 100;
        return goalProgress;
    }

    public void setMonthlyInterest(double interestRatePercent) {
        TRANSACTION_TYPE = "INTEREST (" + interestRatePercent + "%)";
        transactionItem = TRANSACTION_TYPE + ": " + balance;

        double interest = balance *= (interestRatePercent / 100);
        balance += interest;
        addToTransactionHistory(transactionItem);
    }
}
