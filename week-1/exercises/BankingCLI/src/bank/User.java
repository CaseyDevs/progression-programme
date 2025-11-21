package bank;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private BankAccount currentAccount;
    private final List<BankAccount> accounts = new ArrayList<>();

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

    public void addAccount(BankAccount newAccount) {
        if (newAccount == null) {
            throw new IllegalArgumentException("Account cannot be null");
        }
        accounts.add(newAccount);
        // If no current account is set for some reason, set it.
        if (currentAccount == null) {
            currentAccount = newAccount;
        }
    }

    public BankAccount getActiveAccount() {
        if (currentAccount == null) {
            throw new IllegalStateException("No current account selected.");
        }
        return currentAccount;

    }

    public void setActiveAccount(int index){
        if (index < 0 || index >= accounts.size()) {
            throw new IndexOutOfBoundsException("Invalid account index");
        }
        this.currentAccount = accounts.get(index);
    }

    public BankAccount createAccount(double initialBalance, String type) {
        BankAccount acc = new BankAccount(initialBalance, this, type);
        addAccount(acc);
        this.currentAccount = acc;
        return acc;
    }
}
