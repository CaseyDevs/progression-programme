package com.casey.bankingapi.service;

import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.exceptions.AccountNotFoundException;
import com.casey.bankingapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountService {
    private final AccountRepository repo;

    AccountService(AccountRepository repo) {
        this.repo = repo;
    }


    public void createAccount(String accountName, String accountType, BigDecimal balance) {
        repo.addAccount(new AccountResponseDto(accountName, accountType, balance));
    }


    public List<AccountResponseDto> getAllAccounts() throws AccountNotFoundException {
        return repo.getAccounts();
    }
}
