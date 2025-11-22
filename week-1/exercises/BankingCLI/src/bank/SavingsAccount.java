package bank;

public class SavingsAccount extends BankAccount {
    private double savingsGoal;

    public SavingsAccount(double balance, User user, String accountType) {
        super(balance, user, "SAVINGS");  // Inherit constructor param values from BankAccount

    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public double calculateGoalProgress() {
        double goalProgress;
        // double savingsGoal = getSavingsGoal();

        goalProgress = (this.balance / savingsGoal) * 100;
        return goalProgress;

    }
}
