package com.casey.bankingapi;

import com.casey.bankingapi.domain.Account;
import com.casey.bankingapi.dto.UpdateAccountRequestDto;
import com.casey.bankingapi.repository.AccountRepository;
import com.casey.bankingapi.service.AccountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.transaction.support.TransactionTemplate;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class AccountServiceTests {

    @Autowired
    AccountService accountService;

    @Autowired
    AccountRepository accountRepo;

    @Autowired
    TransactionTemplate transactionTemplate;

    @BeforeEach
    public void setUp() {
        accountRepo.deleteAll();
    }

    // Helper method for BigDecimal comparison
    private void assertBigDecimalEquals(BigDecimal expected, BigDecimal actual) {
        assertEquals(0, expected.compareTo(actual),
                "Expected " + expected + " but was " + actual);
    }

    @Test
    @DisplayName("Transaction roll back on exception")
    public void transaction_rolls_back_on_exception() {
        accountService.createAccount("Casey", "Holiday", "SAVINGS", BigDecimal.TEN);

        // Should throw exception
        try {
            accountService.updateAccount("NonExistentAccount", new UpdateAccountRequestDto (
                    "CURRENT",
                    new BigDecimal(100)
            ));
            fail("Expected exception");
        } catch (Exception ignored) {}

        assertBigDecimalEquals(
                BigDecimal.TEN,
                accountRepo.findByAccountName("Holiday").get().getBalance()
        );
    }

    @Test
    @DisplayName("Optimistic locking test")
    public void optimisticLockingTest() {
        accountService.createAccount("Casey", "Holiday", "SAVINGS", BigDecimal.TEN);

        Account acc1 = transactionTemplate.execute(status ->
            accountRepo.findByAccountName("Holiday").get()
        );

        Account acc2 = transactionTemplate.execute(status ->
                accountRepo.findByAccountName("Holiday").get()
        );

        // Perform transactional operation on acc1
        transactionTemplate.executeWithoutResult(status -> {
            acc1.setBalance(new BigDecimal("100"));
            accountRepo.save(acc1);
        });

        // Test should throw an exception trying to update the balance of acc2 (acc1) before persistence version update.
        assertThrows(ObjectOptimisticLockingFailureException.class, () -> {
            transactionTemplate.executeWithoutResult(transactionStatus -> {
               acc2.setBalance(new BigDecimal("200"));
               accountRepo.save(acc2);
            });
        });

    }


}
