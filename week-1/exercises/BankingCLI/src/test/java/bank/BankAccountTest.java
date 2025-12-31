
package bank;

import bank.exceptions.InvalidUserInputException;
import dto.AccountStatementDTO;
import dto.GoalDTO;
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

    @Test
    @DisplayName("Account creation")
    void accountCreation() throws InvalidUserInputException {
        user = new User("C");

        user.createAccount(0, "SAVINGS");
        user.createAccount(0, "STANDARD");

        assertEquals(2, user.getAccountList().size());
    }


    @Test
    @DisplayName("DTO fields are correct")
    void mappingLogic() throws InvalidUserInputException {
        user = new User("John Doe");
        user.createGoal("Save for vacation", 1000.0, "CURRENT");

        BankAccount account = new SavingsAccount(100.0, user);
        BankingService service = new BankingService(user);

        // Add a transaction to test transaction history mapping
        account.deposit(50.0);

        AccountStatementDTO dto = service.mapToAccountStatementDTO(account);

        // Assert all DTO fields are correctly mapped
        assertEquals(account.getAccountDisplayName(), dto.accountName());
        assertEquals(account.getAccountType(), dto.accountType());
        assertEquals(account.getBalance(), dto.balance());
        assertEquals(user.getName(), dto.ownerName());

        // Test transaction history mapping
        assertNotNull(dto.transactionHistory());
        assertFalse(dto.transactionHistory().isEmpty());

        // Test goals mapping
        assertEquals(user.getGoals().size(), dto.goals().size());
        if (!user.getGoals().isEmpty()) {
            GoalDTO firstGoalDTO = dto.goals().get(0);
            Goal firstGoal = user.getGoals().get(0);
            assertEquals(firstGoal.getGoalName(), firstGoalDTO.name());
            assertEquals(firstGoal.getGoalTarget(), firstGoalDTO.target());
            assertEquals(firstGoal.getStartDate(), firstGoalDTO.startDate());
        }
    }
}