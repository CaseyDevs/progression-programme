package bank;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private List<BankAccount> accounts = new ArrayList<>();

    public User(String name, List<BankAccount> accounts) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (accounts == null) {
            throw new IllegalArgumentException("Accounts list cannot be null");
        }

        this.name = name;
        this.accounts.addAll(accounts);
    }

    public String getName(){
        return this.name;
    }

    public List<BankAccount> getAccountList() {
        return new ArrayList<>(accounts);
    }

    public void addAccount(BankAccount newAccount) {
        accounts.add(newAccount);
    }
}
