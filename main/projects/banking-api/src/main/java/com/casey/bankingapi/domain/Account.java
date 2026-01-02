package com.casey.bankingapi.domain;

import java.math.BigDecimal;

public class Account {
    String accountName;
    String accountType;
    BigDecimal balance;

    Account(String accountName, String accountType, BigDecimal balance) {
        this.accountName = accountName;
        this.accountType = accountType;
        this.balance = balance;
    }
}
