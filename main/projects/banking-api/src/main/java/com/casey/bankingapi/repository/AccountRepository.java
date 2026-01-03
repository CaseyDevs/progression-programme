package com.casey.bankingapi.repository;

import com.casey.bankingapi.exceptions.AccountNotFoundException;
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
        return List.copyOf(accounts);
    }

    public Account getAccountByName(String name) throws AccountNotFoundException {
        for (Account acc : accounts) {
            if (acc.getAccountName().equals(name)) {
                return acc;
            }
        }

        throw new AccountNotFoundException("Account doesn't exist");
    }
}
