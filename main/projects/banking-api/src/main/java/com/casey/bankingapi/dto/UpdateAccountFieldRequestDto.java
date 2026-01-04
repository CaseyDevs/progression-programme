package com.casey.bankingapi.dto;

import java.util.Map;

public record UpdateAccountFieldRequestDto(
    Map<String, Object> fields
) {}
