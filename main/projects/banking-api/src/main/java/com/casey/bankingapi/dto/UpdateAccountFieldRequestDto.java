package com.casey.bankingapi.dto;

import java.math.BigDecimal;

public record UpdateAccountFieldRequestDto(
        // Keep nullable
        String accountType,
        BigDecimal balance
) {}
