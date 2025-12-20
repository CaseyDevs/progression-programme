package bank;

public class SavingsAccount extends BankAccount implements SavingsCapable {
    public SavingsAccount(double balance, User user) {
        super(balance, user, "SAVINGS");  // Inherit constructor param values from BankAccount
    }

    @Override
    public void setSavingsGoal(String goalName, double target) {
        User user = getUser();
        user.createGoal(goalName, target, accountName);
    }

    @Override
    public double calculateGoalProgress(double goal) {
        if (goal <= 0) {
            return 0;
        }
        return (this.balance / goal) * 100;
    }


    @Override
    public void setMonthlyInterest(double interestValue) {
        double interest = balance * (interestValue / 100);
        balance += interest;
        String transactionItem = "INTEREST (" + interestValue + "%)" + ": " + interest; // Log only interest, not total balance
        addToTransactionHistory(transactionItem);
    }

    public double getInterestValue(String rate) {
        double interest;

        if (rate.equals("low")) {
            interest = 2.5;
        } else if (rate.equals("medium")){
            interest = 4.5;
        } else {
            interest = 6.0;
        }

        return interest;
    }

    @Override
    public String getInterestRateDescription() {
        return "Choose one of the following interest rate options: \n2.5\n4.5\n6.5";
    }

    @Override
    public boolean canSetSavingsGoal() {
        return true;
    }
}
