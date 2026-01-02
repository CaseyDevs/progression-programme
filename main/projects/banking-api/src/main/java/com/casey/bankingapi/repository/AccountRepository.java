package com.casey.bankingapi.repository;

import org.springframework.stereotype.Repository;
import com.casey.bankingapi.domain.Account;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}
