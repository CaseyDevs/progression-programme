package com.casey.bankingapi.service;

import com.casey.bankingapi.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountService {
    private final List<String> accounts = new ArrayList<>();


    public void createAccount(String name) {
        if (!accounts.contains(name)) {
            accounts.add(name);
        }
    }

    public List<AccountDto> getAllAccounts() {
        return accounts.stream()
                .map(AccountDto::new)
                .collect(Collectors.toList());
    }
}
