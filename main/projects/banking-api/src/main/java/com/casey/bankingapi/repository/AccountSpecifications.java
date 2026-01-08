package com.casey.bankingapi.repository;

import com.casey.bankingapi.domain.Account;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class AccountSpecifications {

    public static Specification<Account> hasType(String type) {
        // Returning null will ignore the filter
        return (root, query, cb) ->
                type == null ? null : cb.equal(root.get("accountType"), type);
    }

    public static Specification<Account> hasMinBalance(BigDecimal minBalance) {
        return (root, query, cb) ->
                minBalance == null ? null :
                        cb.greaterThanOrEqualTo(root.get("balance"), minBalance);
    }
}
