package bank;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double balance, User user, String accountType) {
        super(balance, user, "CURRENT");
    }
}
