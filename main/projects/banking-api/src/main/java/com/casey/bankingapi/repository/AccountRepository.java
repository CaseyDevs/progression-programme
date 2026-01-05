package com.casey.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casey.bankingapi.domain.Account;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByAccountName(String accountName);
}
