package com.casey.bankingapi.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class UpdateAccountRequestDto {
    @NotBlank String accountName;
    @NotBlank String accountType;
    @NotNull BigDecimal balance;
}
