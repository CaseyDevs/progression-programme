package bank;

import java.util.List;

public class User {
    private final String name;
    private List<BankAccount> accounts;

    public User(String name, List<BankAccount> accounts) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
