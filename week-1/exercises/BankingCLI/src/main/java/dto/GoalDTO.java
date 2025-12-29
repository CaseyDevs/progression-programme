package dto;

import java.time.LocalDate;

public record GoalDTO(
        String name,
        double target,
        LocalDate startDate
) {}