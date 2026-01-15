package com.casey.bankingapi.dto;

public record LoginRequestDTO(
        String username,
        String password
) {}
