package com.casey.bankingapi.domain;

import java.math.BigDecimal;

public class Account {
    public String accountName;
    public String accountType;
    public BigDecimal balance;

    public Account(String accountName, String accountType, BigDecimal balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }
}
