
package bank;

import bank.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BankAccount Tests")
public class BankAccountTest {

    // ============ TEST FIXTURES ============
    private User user;
    private BankAccount currentAccount;
    private SavingsAccount savingsAccount;

    @BeforeEach
    @DisplayName("Set up test data before each test")
    void setUp() throws InvalidUserInputException {
        // Fresh setup for each test - this is our TEST FIXTURE
        user = new User("Test User");

        // Create accounts for testing
        user.createAccount(100.0, "CURRENT");  // Start with $100
        currentAccount = user.getCurrentAccount();

        user.createAccount(500.0, "SAVINGS");  // Create savings with $500
        savingsAccount = (SavingsAccount) user.getCurrentAccount();

        // Switch back to current account for most tests
        user.setCurrentAccount(0);
        currentAccount = user.getCurrentAccount();
    }

}