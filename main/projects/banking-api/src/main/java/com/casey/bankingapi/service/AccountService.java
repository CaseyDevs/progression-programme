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


    public List<AccountResponseDto> getAllAccounts() {
        List<Account> accounts = repo.getAccounts();

            // Map accounts to dto
            return accounts.stream()
                    .map(account -> new AccountResponseDto(
                            account.getAccountName(),
                            account.getAccountType(),
                            account.getBalance()
                    ))
                    .collect(Collectors.toList());
    }

    public AccountResponseDto getAccountByName(String name) throws AccountNotFoundException {
        List<Account> accounts = repo.getAccounts();

        for(Account account : accounts) {
            if (account.getAccountName().equals(name)) {
                return new AccountResponseDto(
                        account.getAccountName(),
                        account.getAccountType(),
                        account.getBalance()
                );
            }
        }

        throw new AccountNotFoundException();
    }
}
