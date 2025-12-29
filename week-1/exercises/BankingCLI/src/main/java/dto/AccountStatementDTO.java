package dto;

import java.util.List;

public record AccountStatementDTO (
        String accountName,
        String accountType,
        double balance,
         List<String> transactionHistory,
        List<GoalDTO> goals,
        String ownerName,
        String interestRateDescription
) {}