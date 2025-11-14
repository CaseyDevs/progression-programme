import java.util.ArrayList;
import java.util.List;

public class BankAccount {
    private double balance;
    private List<String> transactionHistory;
    private String TRANSACTION_TYPE;

    public BankAccount(double balance) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = balance;
        this.transactionHistory = new ArrayList<String>();
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        TRANSACTION_TYPE = "DEPOSIT";
        String transactionItem;

        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive");
        }
        balance += amount;
        transactionItem = TRANSACTION_TYPE + ": " + amount;

        addToTransactionHistory(transactionItem);
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        if (balance - amount < 0) {
            return false;
        } else {
            balance -= amount;
            return true;
        }
    }

    public void addToTransactionHistory(String transactionItem) {
        transactionHistory.add(transactionHistory.size(), transactionItem);
    }

    public void getTransactionHistory() {
        for(String s : transactionHistory) {
            System.out.println(s);
        }
    }
}
