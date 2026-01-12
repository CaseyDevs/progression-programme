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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.parameters.P;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import static com.casey.bankingapi.repository.AccountSpecifications.hasMinBalance;
import static com.casey.bankingapi.repository.AccountSpecifications.hasType;

import java.math.BigDecimal;


@Transactional
@Service
public class AccountService {
    private final AccountRepository accountRepo;
    private final UserRepository userRepo;
    private final AuditService auditService;

    AccountService(AccountRepository accountRepo, UserRepository userRepo, AuditService auditService) {
        this.accountRepo = accountRepo;
        this.userRepo = userRepo;
        this.auditService = auditService;
    }

    @PreAuthorize("hasRole('USER')")
    public void createAccount(String userName, String accountName, String accountType, BigDecimal balance) {
        User user = new User(userName);
        Account account = new Account(accountName, accountType, balance);

        user.addAccount(account);
        userRepo.save(user);
    }

    @Transactional(readOnly = true)
    public Page<AccountResponseDto> getAllAccounts(String type, BigDecimal minBalance, Pageable pageable) {
        // Map accounts to dto
        Specification<Account> spec =
                Specification.where(hasType(type))
                        .and(hasMinBalance(minBalance));


        return accountRepo.findAll(spec, pageable)
                    .map(account -> new AccountResponseDto(
                            account.getAccountName(),
                            account.getAccountType(),
                            account.getBalance(),
                            new UserResponseDto(account.getUser().getName())
                    ));
    }

    @Transactional(readOnly = true)
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

    @PreAuthorize("hasRole('USER')")
    public AccountResponseDto updateAccount(String name, UpdateAccountRequestDto request)
            throws AccountNotFoundException {

        Account account = accountRepo.findByAccountName(name)
                .orElseThrow(() ->
                        new AccountNotFoundException("Account with name: " + name + " does not exist")
                );

        account.setAccountType(request.accountType());
        account.setBalance(request.balance());

        try {
            auditService.log("updated account.");
        } catch (RuntimeException e) {
            // TODO: add proper logging
        }
        UserResponseDto userDto = new UserResponseDto(account.getUser().getName());

        return new AccountResponseDto(
                account.getAccountName(),
                account.getAccountType(),
                account.getBalance(),
                userDto
        );
    }

    @PreAuthorize("hasRole('USER')")
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
