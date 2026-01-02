package com.casey.bankingapi.repository;

import com.casey.bankingapi.dto.AccountResponseDto;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {
    private final List<AccountResponseDto> accounts = new ArrayList<>();

    public void addAccount(AccountResponseDto account) {
        accounts.add(account);
    }

    public List<AccountResponseDto> getAccounts() {
        return accounts;
    }
}
