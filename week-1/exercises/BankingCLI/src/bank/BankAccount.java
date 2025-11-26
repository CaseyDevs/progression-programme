package bank;

import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    protected double balance;
    protected final List<String> transactionHistory;
    protected String TRANSACTION_TYPE;
    protected String transactionItem;
    private final User user;
    private final String accountType;


    public BankAccount(double balance, User user, String accountType) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.user = user;
        this.accountType = accountType;
    }

    public User getUser() {
        return user;
    }

    public String getAccountType() {
        return accountType;
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

    // Abstract methods
    public abstract boolean canSetSavingsGoal();
    public abstract String getAccountDisplayName();
}
