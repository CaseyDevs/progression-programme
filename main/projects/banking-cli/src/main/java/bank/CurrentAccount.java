package bank;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double balance, User user) {
        super(balance, user, "CURRENT");
    }
}

