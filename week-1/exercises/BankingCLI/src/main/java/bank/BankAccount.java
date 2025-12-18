package bank;

import bank.exceptions.InvalidUserInputException;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    protected double balance;
    protected final List<String> transactionHistory;
    private final User user;
    private final String accountType;
    protected String accountName;


    public BankAccount(double balance, User user, String accountType) {
        if (balance < 0) {
            throw new IllegalArgumentException("Initial balance cannot be negative");
        }
        this.balance = balance;
        this.transactionHistory = new ArrayList<>();
        this.user = user;
        this.accountType = accountType;
        this.accountName = accountType;
    }

    public User getUser() {
        return user;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountName(String newAccountName) {
        this.accountName = newAccountName;
    }

    public final double getBalance() {
        return this.balance;
    }

    public final void deposit(double amount) throws InvalidUserInputException {
        String transactionItem = "DEPOSIT:" + ": " + amount;

        if (amount <= 0) {
            throw new InvalidUserInputException("Deposit amount must be positive");
        }

        balance += amount;
        addToTransactionHistory(transactionItem);
    }

    public final void withdraw(double amount) throws InvalidUserInputException {
        String transactionItem = "Withdraw" + ": " + amount;

        if (amount <= 0) {
            throw new InvalidUserInputException("Withdrawal amount must be positive");
        }
        if (balance - amount < 0) {
            throw new InvalidUserInputException("Insufficient funds");
        }

        balance -= amount;
        addToTransactionHistory(transactionItem);
    }

    public final void addToTransactionHistory(String transactionItem) {
        transactionHistory.add(transactionHistory.size(), transactionItem);
    }

    public final boolean getTransactionHistory() {
        return !transactionHistory.isEmpty();
    }

    public final void printTransactionHistory() {
        for(String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    }

    public final String getTransactionHistoryAsString() {
        if (transactionHistory.isEmpty()) {
            return "(none)";
        }
        return String.join("\n", transactionHistory);
    }

    public String getAccountDisplayName() {
        return this.accountName;
    }

    @Override
    public String toString() {
        return "### ACCOUNT INFO ###" +
                "\nName: " + this.accountName +
                "\nType: " + accountType +
                "\nOwner: " + user.getName();

    }

    public String generateStatement() {
        return "### BANK STATEMENT ###\n" +
                "\nAccount Name: " + this.accountName +
                "\nAccount Type: " + accountType +
                "\nBalance: $" + balance +
                "\nTransaction History: \n" + getTransactionHistoryAsString() +
                "\nGoals: " + user.getGoals();
    }

    public void setSavingsGoal(double savingsGoal) {
        throw new UnsupportedOperationException();
    }

    public String getInterestRateDescription() {
        throw new UnsupportedOperationException();
    }

    public double calculateGoalProgress() {
        throw new UnsupportedOperationException();
    }

    public double calculateGoalProgress(double goal) {
        throw new UnsupportedOperationException();
    }

    public void setMonthlyInterest(double rate) {
        throw new UnsupportedOperationException();
    }

    // Abstract methods
    public abstract boolean canSetSavingsGoal();
}
