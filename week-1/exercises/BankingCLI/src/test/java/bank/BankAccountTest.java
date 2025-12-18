package bank;

import bank.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private BankAccount account;
    private User user;


    @BeforeEach
    void setUp() throws InvalidUserInputException {
        user = new User("Test user");
        user.createAccount(0.0, "CURRENT");
        account = user.getCurrentAccount();
    }

    @Test
    @DisplayName("Deposit positive amount should increase balance")
    void testDepositPositiveAmount() {
        // Arrange
        double initialBalance = account.getBalance();
        double depositAmount = 100.0;

        // Act
        assertDoesNotThrow(() -> account.deposit(depositAmount));

        // Assert
        assertEquals(initialBalance + depositAmount, account.getBalance(), 0.01);
    }
}
