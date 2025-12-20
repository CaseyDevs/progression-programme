package bank;

public interface SavingsCapable {
    void setSavingsGoal(double goal);
    double calculateGoalProgress(double target);
    void setMonthlyInterest(double rate);
}
