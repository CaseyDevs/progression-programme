package com.casey.bankingapi.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.math.BigDecimal;

@Entity
public class Account {

    // ID generation
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountName;
    private String accountType;
    private BigDecimal balance;

    protected Account() {}

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

    public void setAccountType(String type) {
        this.accountType = type;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }
}
