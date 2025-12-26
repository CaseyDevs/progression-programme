package dto;

import java.time.LocalDate;

public class GoalDTO {
    private String name;
    private double target;
    private LocalDate startDate;

    public GoalDTO() {}

    public GoalDTO(String name, double target, LocalDate startDate) {
        this.name = name;
        this.target = target;
        this.startDate = startDate;
    }

    public String getGoalName() {
        return this.name;
    }

    public void setGoalName(String name) {
        this.name = name;
    }

    public double getGoalTarget() {
        return this.target;
    }

    public void setGoalTarget(double target) {
        this.target = target;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        return "GoalDTO{" +
                "name='" + name + '\'' + 
                ", target=" + target +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
