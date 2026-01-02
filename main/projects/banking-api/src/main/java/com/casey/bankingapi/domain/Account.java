package com.casey.bankingapi.domain;

import java.math.BigDecimal;

public class Account {
    private final String accountName;
    private final String accountType;
    private BigDecimal balance;

    public Account(String accountName, String accountType, BigDecimal balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getAccountName() {
        return accountName;
    }

    public String getAccountType() {
        return accountType;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
