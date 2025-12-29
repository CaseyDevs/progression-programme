package dto;

import java.time.LocalDateTime;

public record TransactionDTO(
    String transactionId,
    String type,
    double amount,
    String description,
    LocalDateTime timestamp,
    String accountName
) {}
