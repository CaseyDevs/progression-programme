package bank;

public interface SavingsCapable {
    void setSavingsGoal(String goalName, double target );
    double calculateGoalProgress(double target);
    void setMonthlyInterest(double rate);
}
