package com.casey.bankingapi;

import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.repository.AccountRepository;
import com.casey.bankingapi.service.AccountService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.math.BigDecimal;


@SpringBootTest
@Transactional
public class AccountServiceTests {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepo;

    @DisplayName("Transaction roll back on exception")
    public void transaction_rolls_back_on_exception() {
        accountService.createAccount("Casey", "Holiday", "SAVINGS", BigDecimal.TEN);

        try {
            accountService.updateAccount("Holiday", new UpdateAccountRequestDto (
                    "CURRENT",
                    new BigDecimal(100)
            ));
            fail("Expexted exception");
        } catch (Exception ignored) {}

        assertEquals(
                BigDecimal.TEN,
                accountRepo.findByAccountName("main").get().getBalance()
        );

    }
}
