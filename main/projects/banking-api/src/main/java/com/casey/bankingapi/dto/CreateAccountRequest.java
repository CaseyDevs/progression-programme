package com.casey.bankingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record CreateAccountRequest(
        @NotBlank String accountName,
        @NotBlank String accountType,
        @NotNull @Positive BigDecimal balance
) {}
