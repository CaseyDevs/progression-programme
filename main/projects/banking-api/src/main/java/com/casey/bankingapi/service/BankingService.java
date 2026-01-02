package com.casey.bankingapi.service;

import com.casey.bankingapi.dto.AccountDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BankingService {
    private final List<String> accounts = new ArrayList<>();

    public String getWelcomeMessage(String name) {
        return "Welcome " + name + "!";
    }

    public void createAccount(String name) {
        if (!accounts.contains(name)) {
            accounts.add(name);
        }
        // Could throw exception if account already exists
    }

    public List<AccountDto> getAllAccounts() {
        return accounts.stream()
                .map(AccountDto::new)
                .collect(Collectors.toList());
    }
}
