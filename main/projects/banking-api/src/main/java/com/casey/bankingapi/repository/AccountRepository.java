package com.casey.bankingapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.casey.bankingapi.domain.Account;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>, JpaSpecificationExecutor<Account> {

    Optional<Account> findByAccountName(String accountName);
}
