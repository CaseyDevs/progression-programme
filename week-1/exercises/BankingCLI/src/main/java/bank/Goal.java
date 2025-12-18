package bank;

import java.time.LocalDate;

public class Goal {
    private final String name;
    private final double target;
    private final LocalDate startDate;

    public Goal(String name, double target, String accountName) {
        this.name = name;
        this.target = target;
        this.startDate = LocalDate.now();
    }

    public String getGoalName() {
        return this.name;
    }

    public double getGoalTarget() {
        return this.target;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    @Override
    public String toString() {
        return "\n- Name: " + getGoalName() + "\n- Savings Target: " + getGoalTarget() + "\n- Start date: " + getStartDate();
    }
}
