package com.casey.bankingapi.service;

import com.casey.bankingapi.domain.Account;
import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.exceptions.AccountNotFoundException;
import com.casey.bankingapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repo;

    AccountService(AccountRepository repo) {
        this.repo = repo;
    }


    public void createAccount(String accountName, String accountType, BigDecimal balance) {
        repo.addAccount(new Account(accountName, accountType, balance));
    }


    public List<AccountResponseDto> getAllAccounts() throws AccountNotFoundException {
        List<Account> accounts = repo.getAccounts();

        if (!accounts.isEmpty()) {
            // Map accounts to dto
            return accounts.stream()
                    .map(account -> new AccountResponseDto(
                            account.accountName,
                            account.accountType,
                            account.balance
                    ))
                    .collect(Collectors.toList());
        } else {
            throw new AccountNotFoundException();
        }
    }
}
