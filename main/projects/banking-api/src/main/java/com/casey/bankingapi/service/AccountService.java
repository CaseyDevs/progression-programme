package com.casey.bankingapi.service;

import com.casey.bankingapi.domain.Account;
import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.exceptions.AccountNotFoundException;
import com.casey.bankingapi.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

        // Filter by name & create new AccountResponseDto
        return repo.getAccounts().stream().
                filter(account -> account.getAccountName().equals(name))
                .findFirst()
                .map(account -> new AccountResponseDto(
                        account.getAccountName(),
                        account.getAccountType(),
                        account.getBalance()
                ))
                .orElseThrow(() -> new AccountNotFoundException("Account with name " + name + " not found."));
    }

    public AccountResponseDto updateAccount(String name, UpdateAccountRequestDto request)
            throws AccountNotFoundException {

        Account account = repo.getAccountByName(name);

        account.setAccountName(request.accountName());
        account.setAccountType(request.accountType());
        account.setBalance(request.balance());

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance()
        );
    }
}
