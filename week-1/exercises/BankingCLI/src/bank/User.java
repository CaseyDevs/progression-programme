package bank;

import bank.exceptions.InvalidUserInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private BankAccount currentAccount;
    private final List<BankAccount> accounts = new ArrayList<>();
    private final List<Goal> goals = new ArrayList<>();

    public User(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public List<BankAccount> getAccountList() {
        return new ArrayList<>(accounts);
    }

    public int getAccountListSize() {
        return accounts.size();
    }

    public void addAccount(BankAccount newAccount) throws InvalidUserInputException {
        if (newAccount == null) {
            throw new InvalidUserInputException("Account cannot be null");
        }
        accounts.add(newAccount);
        if (currentAccount == null) {
            currentAccount = newAccount;
        }
    }

    public BankAccount getCurrentAccount() {
        if (currentAccount == null) {
            throw new IllegalStateException("No current account selected.");
        }
        return currentAccount;
    }

    public void setCurrentAccount(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= accounts.size()) {
            throw new IndexOutOfBoundsException("Account index out of range.");
        }
        this.currentAccount = accounts.get(index);
    }

    // Create either a standard BankAccount or a SavingsAccount based on type
    public void createAccount(double initialBalance, String type) throws InvalidUserInputException {
        BankAccount acc;
        if ("SAVINGS".equalsIgnoreCase(type)) {
            acc = new SavingsAccount(initialBalance, this);
        } else {
            acc = new CurrentAccount(initialBalance, this);
        }
        addAccount(acc);
        this.currentAccount = acc;
    }

    public void createGoal(String name, double target, LocalDate startDate) {
        Goal newGoal = new Goal(name, target, startDate);
        goals.add(newGoal);
    }
}
