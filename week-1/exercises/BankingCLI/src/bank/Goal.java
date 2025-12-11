package bank;

import java.time.LocalDate;

public class Goal {
    private final String name;
    private double target;
    private LocalDate startDate;

    public Goal(String name, double target, LocalDate startDate) {
        this.name = name;
        this.target = target;
        this.startDate = startDate;
    }

    public String getGoalName() {
        return this.name;
    }

    public double getGoalTarget() {
        return this.target;
    }

    public void setGoalTarget(double target) {
        this.target = target;
    }

    public LocalDate getStartDate() {
        return LocalDate.now();
    }

    public void setStartDate() {
        this.startDate = LocalDate.now();
    }
}
