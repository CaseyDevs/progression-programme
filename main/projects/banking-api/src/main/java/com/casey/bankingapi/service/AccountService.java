package com.casey.bankingapi.service;

import com.casey.bankingapi.dto.AccountResponseDto;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final List<AccountResponseDto> accounts = new ArrayList<>();


    public void createAccount(String accountName, String accountType, BigDecimal balance) {
        accounts.add(new AccountResponseDto(accountName, accountType, balance));
    }

    public List<AccountResponseDto> getAllAccounts() {
        return new ArrayList<>(accounts);
    }
}
