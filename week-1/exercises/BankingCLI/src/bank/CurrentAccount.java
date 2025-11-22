package bank;

public class CurrentAccount extends BankAccount {

    public CurrentAccount(double balance, User user, String accountType) {
        super(balance, user, "CURRENT");
    }

    @Override
    public double getInterestRate() {
        return 2.5;
    }

    @Override
    public String getInterestRateDescription() {
        return "Standard rate applied (2.5%)";
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

