package com.casey.bankingapi.security;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import com.casey.bankingapi.repository.AccountRepository;


@Component
public class AccountSecurity {
    
    private final AccountRepository accountRepository;

    public AccountSecurity(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public boolean isOwner(String accountName, Authentication authentication) {
        return accountRepository.findByAccountName(accountName).map(account -> 
            account.getUser().getName().equals(authentication.getName())
        )
        .orElse(false);
    }
}
