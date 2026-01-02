package com.casey.bankingapi.dto;

import java.math.BigDecimal;

public record AccountResponseDto(
        String accountName,
        String accountType,
        BigDecimal balance
) {}
