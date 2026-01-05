package com.casey.bankingapi.service;

import com.casey.bankingapi.domain.Account;
import com.casey.bankingapi.dto.AccountResponseDto;
import com.casey.bankingapi.dto.UpdateAccountFieldRequestDto;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.dto.UserResponseDto;
import com.casey.bankingapi.exceptions.AccountNotFoundException;
import com.casey.bankingapi.domain.User;
import com.casey.bankingapi.repository.AccountRepository;
import com.casey.bankingapi.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@Service
public class AccountService {
    private final AccountRepository accountRepo;
    private final UserRepository userRepo;

    AccountService(AccountRepository accountRepo, UserRepository userRepo) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
    }

    public void createAccount(String userName, String accountName, String accountType, BigDecimal balance) {
        User newUser = new User(userName);

        userRepo.save(newUser);

        User user = userRepo.findByName(userName)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Account account = new Account(accountName, accountType, balance);
        user.addAccount(account);

        userRepo.save(user);
    }

    public List<AccountResponseDto> getAllAccounts() {
        // Map accounts to dto
            return accountRepo.findAll().stream()
                    .map(account -> new AccountResponseDto(
                            account.getAccountName(),
                            account.getAccountType(),
                            account.getBalance(),
                            new UserResponseDto(account.getUser().getName())
                    ))
                    .collect(Collectors.toList());
    }

    public AccountResponseDto getAccountByName(String name) {
        Account account = accountRepo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name " + name + " not found")
                );

        UserResponseDto userDto = new UserResponseDto(account.getUser().getName());

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance(),
                userDto
        );
    }

    public AccountResponseDto updateAccount(String name, UpdateAccountRequestDto request)
            throws AccountNotFoundException {

        Account account = accountRepo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name: " + name + " does not exist")
                );

        account.setAccountType(request.accountType());
        account.setBalance(request.balance());

        UserResponseDto userDto = new UserResponseDto(account.getUser().getName());

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance(),
                userDto
        );
    }

    public AccountResponseDto updateAccountField(String name, UpdateAccountFieldRequestDto request)
                throws AccountNotFoundException {

        Account account = accountRepo.findByAccountName(name)
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

        UserResponseDto userDto = new UserResponseDto(account.getUser().getName());

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance(),
                userDto
        );
    }
}
