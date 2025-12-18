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

    @Test
    @DisplayName("Deposit should add transaction to history")
    void testDepositAddsTransactionHistory() throws InvalidUserInputException {
        // Arrange
        double depositAmount = 50.0;
        int intialHistorySize = account.getTransactionHistory() ? 1 : 0; // returns bool so return true or false

        // Act
        account.deposit(depositAmount);

        // Assert
        assertTrue(account.getTransactionHistory());
    }

    @Test
    @DisplayName("Deposit zero amount should throw InvalidUserInputException")
    void testDepositZeroAmount() {
        // Act & Assert
        InvalidUserInputException exception = assertThrows(
                InvalidUserInputException.class,
                () -> account.deposit(0.0)
        );

        // Assert
        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Deposit negative amount should throw InvalidUserInputException")
    void testDepositNegativeAmount() {
        // Act & Assert
        InvalidUserInputException exception = assertThrows(
                InvalidUserInputException.class,
                () -> account.deposit(-50.0)
        );

        assertEquals("Deposit amount must be positive", exception.getMessage());
    }

    @Test
    @DisplayName("Failed deposit should not change balance")
    void testFailedDepositDoesNotChangeBalance() {
        // Arrange
        double initialBalance = account.getBalance();

        // Act & Assert
        assertThrows(InvalidUserInputException.class, () -> account.deposit(-10.0));
        assertEquals(initialBalance, account.getBalance(), 0.01);
    }
}
