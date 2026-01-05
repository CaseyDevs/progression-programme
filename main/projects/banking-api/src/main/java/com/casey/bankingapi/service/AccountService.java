package com.casey.bankingapi.service;

import com.casey.bankingapi.domain.Account;
import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.UpdateAccountFieldRequestDto;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.exceptions.AccountNotFoundException;
import com.casey.bankingapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final AccountRepository repo;

    AccountService(AccountRepository repo) {
        this.repo = repo;
    }


    public void createAccount(String accountName, String accountType, BigDecimal balance) {
        repo.save(new Account(accountName, accountType, balance));
    }


    public List<AccountResponseDto> getAllAccounts() {
            // Map accounts to dto
            return repo.findAll().stream()
                    .map(account -> new AccountResponseDto(
                            account.getAccountName(),
                            account.getAccountType(),
                            account.getBalance()
                    ))
                    .collect(Collectors.toList());
    }

    public AccountResponseDto getAccountByName(String name) {
        Account account = repo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name " + name + " not found")
                );

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance()
        );
    }

    public AccountResponseDto updateAccount(String name, UpdateAccountRequestDto request)
            throws AccountNotFoundException {

        Account account = repo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name: " + name + " does not exist")
                );

        account.setAccountType(request.accountType());
        account.setBalance(request.balance());

        repo.save(account);

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance()
        );
    }

    public AccountResponseDto updateAccountField(String name, UpdateAccountFieldRequestDto request)
                throws AccountNotFoundException {

        Account account = repo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name: " + name + " does not exist")
                );

        if(request.accountType() != null) {
            account.setAccountType(request.accountType());
        }

        if (request.balance() != null) {
            if (request.balance().signum() < 0) {
                throw new IllegalArgumentException("Balance cannot be negative");
            }
            account.setBalance(request.balance());
        }

        repo.save(account);

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance()
        );
    }
}
