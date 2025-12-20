package bank;

public interface SavingsCapable {
    void setSavingsGoal(double goal);
    double calculateGoalProgress();
    void setMonthlyInterest(double rate);
}
