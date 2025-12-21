
package bank;

import bank.exceptions.InvalidUserInputException;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("BankAccount Tests")
public class BankAccountTest {
    private User user;

    @Test
    @DisplayName("Deposit increases balance")
    void depositIncreasesBalance() throws InvalidUserInputException {
        BankAccount currentAccount = new CurrentAccount(0.0, user);

        currentAccount.deposit(50.0); // Bal = 50.00

        assertEquals(50.0, currentAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw reduces balance")
    void withdrawReducesBalance() throws InvalidUserInputException {
        BankAccount currentAccount = new CurrentAccount(100.0, user);
        double initialBalance = currentAccount.getBalance();

        currentAccount.withdraw(50.0);

        assertEquals(50.0, currentAccount.getBalance());
    }

    @Test
    @DisplayName("Withdraw can not cause negative balance")
    void withdrawPreventsLessThanZero() throws InvalidUserInputException {
        BankAccount currentAccount = new CurrentAccount(50.0, user);

        assertThrows(InvalidUserInputException.class, () -> {
            currentAccount.withdraw(100.0);
        });
    }

    @Test
    @DisplayName("Savings interest increases balance")
    void savingsInterestIncreasesBalance() {
        BankAccount savingsAccount = new SavingsAccount(100.0, user);

        savingsAccount.setMonthlyInterest(10);

        assertEquals(110, savingsAccount.getBalance());
    }
}