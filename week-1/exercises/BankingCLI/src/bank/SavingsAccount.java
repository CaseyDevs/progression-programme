package bank;

public class SavingsAccount extends BankAccount {
    private static final double SAVINGS_INTEREST_RATE = 4.5;
    private double savingsGoal;

    public SavingsAccount(double balance, User user) {
        super(balance, user, "SAVINGS");  // Inherit constructor param values from BankAccount
    }

    public void setSavingsGoal(double savingsGoal) {
        this.savingsGoal = savingsGoal;
    }

    public double getSavingsGoal() {
        return savingsGoal;
    }

    public double calculateGoalProgress() {
        if (savingsGoal <= 0) {
            return 0;
        }
        return (this.balance / savingsGoal) * 100;
    }

    public void setMonthlyInterest() {
        double interest = balance * (SAVINGS_INTEREST_RATE / 100);
        balance += interest;
        TRANSACTION_TYPE = "INTEREST (" + SAVINGS_INTEREST_RATE + "%)";
        transactionItem = TRANSACTION_TYPE + ": " + interest; // Log only interest, not total balance
        addToTransactionHistory(transactionItem);
    }

    public double getInterestRate() {
        return SAVINGS_INTEREST_RATE;
    }

    public String getInterestRateDescription() {
        return "Savings rate applied (" + SAVINGS_INTEREST_RATE + "%)";
    }

    @Override
    public boolean canSetSavingsGoal() {
        return true;
    }

    @Override
    public String getAccountDisplayName() {
        return "Savings Account";
    }
}
