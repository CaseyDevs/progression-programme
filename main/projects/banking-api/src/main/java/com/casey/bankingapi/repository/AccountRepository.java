package com.casey.bankingapi.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountRepository {
    private final List<Account> accounts = new ArrayList<>();
}
