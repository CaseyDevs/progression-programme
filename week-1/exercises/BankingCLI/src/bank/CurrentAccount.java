package bank;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double balance, User user) {
        super(balance, user, "CURRENT");
    }

    @Override
    public boolean canSetSavingsGoal() {
        return false;
    }

    @Override
    public String getAccountDisplayName() {
        return "Current Account";
    }
}

