
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

    @AfterEach
    @DisplayName("Clean up after each test")
    void tearDown() {
        // Optional cleanup - JUnit handles most of this automatically
        user = null;
        currentAccount = null;
        savingsAccount = null;
    }

    // ============ DEPOSIT METHOD TESTS ============

    @Nested
    @DisplayName("Deposit Method Tests")
    class DepositTests {

        @Test
        @DisplayName("🟢 Valid deposit should increase balance")
        void testValidDeposit() throws InvalidUserInputException {
            // ARRANGE - Set up test data
            double initialBalance = currentAccount.getBalance();  // $100
            double depositAmount = 50.0;

            // ACT - Execute the method being tested
            currentAccount.deposit(depositAmount);

            // ASSERT - Verify the expected outcome
            assertEquals(initialBalance + depositAmount, currentAccount.getBalance(), 0.01,
                    "Balance should increase by deposit amount");

            // Additional assertion - check transaction history
            assertTrue(currentAccount.getTransactionHistory(),
                    "Should have transaction history after deposit");
        }

        @Test
        @DisplayName("🟢 Small deposit should work correctly")
        void testSmallDeposit() throws InvalidUserInputException {
            double initialBalance = currentAccount.getBalance();
            double smallAmount = 0.01;  // 1 cent

            assertDoesNotThrow(() -> currentAccount.deposit(smallAmount),
                    "Should be able to deposit small amounts");

            assertEquals(initialBalance + smallAmount, currentAccount.getBalance(), 0.001,
                    "Balance should increase by small deposit amount");
        }

        @Test
        @DisplayName("🟢 Large deposit should work correctly")
        void testLargeDeposit() throws InvalidUserInputException {
            double initialBalance = currentAccount.getBalance();
            double largeAmount = 10000.0;

            assertDoesNotThrow(() -> currentAccount.deposit(largeAmount),
                    "Should be able to deposit large amounts");

            assertEquals(initialBalance + largeAmount, currentAccount.getBalance(), 0.01,
                    "Balance should increase by large deposit amount");
        }

        @Test
        @DisplayName("🔴 Zero deposit should throw exception")
        void testZeroDeposit() {
            // ACT & ASSERT - Test exception handling
            InvalidUserInputException exception = assertThrows(
                    InvalidUserInputException.class,
                    () -> currentAccount.deposit(0.0),
                    "Depositing zero should throw InvalidUserInputException"
            );

            assertEquals("Deposit amount must be positive", exception.getMessage(),
                    "Exception message should match expected text");
        }

        @Test
        @DisplayName("🔴 Negative deposit should throw exception")
        void testNegativeDeposit() {
            InvalidUserInputException exception = assertThrows(
                    InvalidUserInputException.class,
                    () -> currentAccount.deposit(-25.0),
                    "Negative deposit should throw InvalidUserInputException"
            );

            assertEquals("Deposit amount must be positive", exception.getMessage(),
                    "Exception message should be correct for negative amounts");
        }

        @Test
        @DisplayName("🟢 Failed deposit should not change balance")
        void testFailedDepositDoesNotChangeBalance() {
            double initialBalance = currentAccount.getBalance();

            // Try invalid deposit
            assertThrows(InvalidUserInputException.class,
                    () -> currentAccount.deposit(-10.0));

            // Balance should remain unchanged
            assertEquals(initialBalance, currentAccount.getBalance(), 0.01,
                    "Balance should not change after failed deposit");
        }

        @Test
        @DisplayName("🟢 Multiple deposits should accumulate correctly")
        void testMultipleDeposits() throws InvalidUserInputException {
            double initialBalance = currentAccount.getBalance();  // $100

            // Make multiple deposits
            currentAccount.deposit(25.0);   // $125
            currentAccount.deposit(75.0);   // $200
            currentAccount.deposit(100.0);  // $300

            double expectedBalance = initialBalance + 25.0 + 75.0 + 100.0;
            assertEquals(expectedBalance, currentAccount.getBalance(), 0.01,
                    "Multiple deposits should accumulate correctly");

            assertTrue(currentAccount.getTransactionHistory(),
                    "Should have transaction history after multiple deposits");
        }

        @Test
        @DisplayName("🟢 Deposit should add correct transaction to history")
        void testDepositTransactionHistory() throws InvalidUserInputException {
            double depositAmount = 75.0;

            currentAccount.deposit(depositAmount);

            // Check that transaction history contains the deposit
            assertTrue(currentAccount.getTransactionHistory(),
                    "Transaction history should not be empty after deposit");

            String historyString = currentAccount.getTransactionHistoryAsString();
            assertTrue(historyString.contains("DEPOSIT: " + depositAmount),
                    "Transaction history should contain the deposit record");
        }

        @Test
        @DisplayName("🟢 Decimal deposits should work correctly")
        void testDecimalDeposit() throws InvalidUserInputException {
            double initialBalance = currentAccount.getBalance();
            double decimalAmount = 123.45;

            currentAccount.deposit(decimalAmount);

            assertEquals(initialBalance + decimalAmount, currentAccount.getBalance(), 0.01,
                    "Decimal deposit amounts should work correctly");
        }

        @Test
        @DisplayName("🟢 Deposit to empty account should work")
        void testDepositToEmptyAccount() throws InvalidUserInputException {
            // Create account with zero balance
            user.createAccount(0.0, "CURRENT");
            BankAccount emptyAccount = user.getCurrentAccount();

            double depositAmount = 50.0;
            emptyAccount.deposit(depositAmount);

            assertEquals(depositAmount, emptyAccount.getBalance(), 0.01,
                    "Deposit to empty account should set balance to deposit amount");
        }
    }

    // ============ WITHDRAW METHOD TESTS ============

    @Nested
    @DisplayName("Withdraw Method Tests")
    class WithdrawTests {

        @Test
        @DisplayName("🟢 Valid withdrawal should decrease balance")
        void testValidWithdrawal() throws InvalidUserInputException {
            // ARRANGE - Set up test data
            double initialBalance = currentAccount.getBalance();  // $100
            double withdrawAmount = 30.0;

            // ACT - Execute the method being tested
            currentAccount.withdraw(withdrawAmount);

            // ASSERT - Verify the expected outcome
            assertEquals(initialBalance - withdrawAmount, currentAccount.getBalance(), 0.01,
                    "Balance should decrease by withdrawal amount");

            // Additional assertion - check transaction history
            assertTrue(currentAccount.getTransactionHistory(),
                    "Should have transaction history after withdrawal");
        }

        @Test
        @DisplayName("🔴 Zero withdrawal should throw exception")
        void testZeroWithdrawal() {
            // ACT & ASSERT - Test exception handling
            InvalidUserInputException exception = assertThrows(
                    InvalidUserInputException.class,
                    () -> currentAccount.withdraw(0.0),
                    "Withdrawing zero should throw InvalidUserInputException"
            );

            assertEquals("Withdrawal amount must be positive", exception.getMessage(),
                    "Exception message should match expected text");
        }

        @Test
        @DisplayName("🔴 Negative withdrawal should throw exception")
        void testNegativeWithdrawal() {
            InvalidUserInputException exception = assertThrows(
                    InvalidUserInputException.class,
                    () -> currentAccount.withdraw(-25.0)
            );

            assertEquals("Withdrawal amount must be positive", exception.getMessage());
        }

        @Test
        @DisplayName("🔴 Insufficient funds should throw exception")
        void testInsufficientFunds() {
            // Try to withdraw more than available balance ($100)
            InvalidUserInputException exception = assertThrows(
                    InvalidUserInputException.class,
                    () -> currentAccount.withdraw(150.0)
            );

            assertEquals("Insufficient funds", exception.getMessage());
        }

        @Test
        @DisplayName("🟢 Failed withdrawal should not change balance")
        void testFailedWithdrawalDoesNotChangeBalance() {
            double initialBalance = currentAccount.getBalance();

            // Try invalid withdrawal
            assertThrows(InvalidUserInputException.class,
                    () -> currentAccount.withdraw(-50.0));

            // Balance should remain unchanged
            assertEquals(initialBalance, currentAccount.getBalance(), 0.01,
                    "Balance should not change after failed withdrawal");
        }

        @Test
        @DisplayName("🟢 Exact balance withdrawal should work")
        void testWithdrawExactBalance() throws InvalidUserInputException {
            double exactBalance = currentAccount.getBalance();

            assertDoesNotThrow(() -> currentAccount.withdraw(exactBalance),
                    "Should be able to withdraw exact balance");

            assertEquals(0.0, currentAccount.getBalance(), 0.01,
                    "Balance should be zero after withdrawing everything");
        }
    }

    // ============ GOAL PROGRESSION TESTS ============

    @Nested
    @DisplayName("Goal Progress Tests")
    class GoalProgressTests {

        @BeforeEach
        void setUpGoals() {
            // Create some goals for testing
            user.createGoal("Vacation Fund", 1000.0, "SAVINGS");
            user.createGoal("Emergency Fund", 2000.0, "SAVINGS");
        }

        @Test
        @DisplayName("🟢 Goal progress calculation should be accurate")
        void testGoalProgressCalculation() {
            // Savings account has $500, goal is $1000
            double progress = savingsAccount.calculateGoalProgress(1000.0);

            assertEquals(50.0, progress, 0.01,
                    "Progress should be 50% when balance is $500 and goal is $1000");
        }

        @Test
        @DisplayName("🟢 Goal progress over 100% should work")
        void testGoalProgressOver100Percent() {
            // Savings account has $500, set a smaller goal
            double progress = savingsAccount.calculateGoalProgress(250.0);

            assertEquals(200.0, progress, 0.01,
                    "Progress should be 200% when balance exceeds goal");
        }

        @Test
        @DisplayName("🔴 Zero or negative goal should return 0% progress")
        void testInvalidGoalProgress() {
            assertEquals(0.0, savingsAccount.calculateGoalProgress(0.0), 0.01,
                    "Zero goal should return 0% progress");

            assertEquals(0.0, savingsAccount.calculateGoalProgress(-100.0), 0.01,
                    "Negative goal should return 0% progress");
        }

        @Test
        @DisplayName("🟢 Progress should update after deposits")
        void testProgressUpdatesAfterDeposit() throws InvalidUserInputException {
            double initialProgress = savingsAccount.calculateGoalProgress(1000.0);

            savingsAccount.deposit(250.0);  // Add $250 to $500 = $750

            double newProgress = savingsAccount.calculateGoalProgress(1000.0);

            assertTrue(newProgress > initialProgress,
                    "Progress should increase after deposit");
            assertEquals(75.0, newProgress, 0.01,
                    "New progress should be 75%");
        }

        @Test
        @DisplayName("🟢 Progress should update after withdrawals")
        void testProgressUpdatesAfterWithdrawal() throws InvalidUserInputException {
            double initialProgress = savingsAccount.calculateGoalProgress(1000.0);  // 50%

            savingsAccount.withdraw(100.0);  // Remove $100 from $500 = $400

            double newProgress = savingsAccount.calculateGoalProgress(1000.0);

            assertTrue(newProgress < initialProgress,
                    "Progress should decrease after withdrawal");
            assertEquals(40.0, newProgress, 0.01,
                    "New progress should be 40%");
        }
    }

    // ============ INTEREST APPLICATION TESTS ============

    @Nested
    @DisplayName("Interest Application Tests")
    class InterestTests {

        @Test
        @DisplayName("🟢 Interest should be correctly calculated and applied")
        void testInterestCalculation() throws InvalidUserInputException {
            double initialBalance = savingsAccount.getBalance();  // $500
            double interestRate = 4.5;  // 4.5%

            savingsAccount.setMonthlyInterest(interestRate);

            double expectedInterest = initialBalance * (interestRate / 100);  // $22.50
            double expectedNewBalance = initialBalance + expectedInterest;     // $522.50

            assertEquals(expectedNewBalance, savingsAccount.getBalance(), 0.01,
                    "Balance should include calculated interest");
        }

        @Test
        @DisplayName("🟢 Interest should be added to transaction history")
        void testInterestInTransactionHistory() {
            double interestRate = 2.5;

            assertDoesNotThrow(() -> savingsAccount.setMonthlyInterest(interestRate));

            assertTrue(savingsAccount.getTransactionHistory(),
                    "Should have transaction history after interest application");

            String historyString = savingsAccount.getTransactionHistoryAsString();
            assertTrue(historyString.contains("INTEREST"),
                    "Transaction history should contain interest entry");
        }

        @Test
        @DisplayName("🟢 Multiple interest applications should compound")
        void testCompoundInterest() throws InvalidUserInputException {
            double initialBalance = savingsAccount.getBalance();

            // Apply interest twice
            savingsAccount.setMonthlyInterest(2.0);  // 2% interest
            double balanceAfterFirst = savingsAccount.getBalance();

            savingsAccount.setMonthlyInterest(2.0);  // Another 2% on the new balance
            double finalBalance = savingsAccount.getBalance();

            // Verify compounding effect
            assertTrue(finalBalance > balanceAfterFirst,
                    "Second interest application should compound");

            double expectedFinal = initialBalance * 1.02 * 1.02;  // Compound calculation
            assertEquals(expectedFinal, finalBalance, 0.01,
                    "Final balance should reflect compound interest");
        }

        @Test
        @DisplayName("🔴 Current account should not support interest")
        void testCurrentAccountInterestRestriction() {
            // Current accounts should throw UnsupportedOperationException
            assertThrows(UnsupportedOperationException.class,
                    () -> currentAccount.setMonthlyInterest(2.5),
                    "Current accounts should not support interest rates");
        }
    }

    // ============ INTEGRATION TESTS ============

    @Nested
    @DisplayName("Integration Tests")
    class IntegrationTests {

        @Test
        @DisplayName("🟢 Complete banking workflow should work")
        void testCompleteBankingWorkflow() throws InvalidUserInputException {
            // Start with fresh savings account
            user.createAccount(1000.0, "SAVINGS");
            SavingsAccount account = (SavingsAccount) user.getCurrentAccount();

            // Create a goal
            user.createGoal("Dream House", 5000.0, "SAVINGS");

            // Make some transactions
            account.deposit(500.0);      // Balance: $1500
            account.withdraw(200.0);     // Balance: $1300
            account.setMonthlyInterest(3.0);  // Add 3% interest

            // Verify final state
            double expectedBalance = 1300.0 + (1300.0 * 0.03);  // $1339
            assertEquals(expectedBalance, account.getBalance(), 0.01);

            // Check goal progress
            double progress = account.calculateGoalProgress(5000.0);
            assertTrue(progress > 25.0, "Should have made progress towards goal");

            // Verify transaction history
            assertTrue(account.getTransactionHistory(), "Should have transaction history");
        }

        @Test
        @DisplayName("🟢 Deposit and withdrawal combination should work")
        void testDepositWithdrawalCombination() throws InvalidUserInputException {
            double initialBalance = currentAccount.getBalance();  // $100

            // Series of deposits and withdrawals
            currentAccount.deposit(200.0);   // $300
            currentAccount.withdraw(50.0);   // $250
            currentAccount.deposit(100.0);   // $350
            currentAccount.withdraw(25.0);   // $325

            double expectedFinalBalance = initialBalance + 200.0 - 50.0 + 100.0 - 25.0;
            assertEquals(expectedFinalBalance, currentAccount.getBalance(), 0.01,
                    "Final balance should reflect all transactions");

            assertTrue(currentAccount.getTransactionHistory(),
                    "Should have complete transaction history");
        }
    }

    // ============ PARAMETERIZED TESTS ============

    @ParameterizedTest
    @DisplayName("🟢 Various valid deposit amounts should work correctly")
    @ValueSource(doubles = {0.01, 10.0, 25.5, 50.0, 100.0, 1000.0})
    void testVariousValidDeposits(double amount) throws InvalidUserInputException {
        double initialBalance = currentAccount.getBalance();

        currentAccount.deposit(amount);

        assertEquals(initialBalance + amount, currentAccount.getBalance(), 0.01,
                "Balance should increase by deposit amount: " + amount);
    }

    @ParameterizedTest
    @DisplayName("🔴 Invalid deposit amounts should throw exceptions")
    @ValueSource(doubles = {0.0, -0.01, -1.0, -50.0, -1000.0})
    void testInvalidDeposits(double amount) {
        assertThrows(InvalidUserInputException.class,
                () -> currentAccount.deposit(amount),
                "Invalid deposit amount should throw exception: " + amount);
    }

    @ParameterizedTest
    @DisplayName("🟢 Various withdrawal amounts should work correctly")
    @ValueSource(doubles = {10.0, 25.5, 50.0, 99.99})
    void testVariousValidWithdrawals(double amount) throws InvalidUserInputException {
        double initialBalance = currentAccount.getBalance();

        currentAccount.withdraw(amount);

        assertEquals(initialBalance - amount, currentAccount.getBalance(), 0.01);
    }

    @ParameterizedTest
    @DisplayName("🔴 Invalid withdrawal amounts should throw exceptions")
    @ValueSource(doubles = {0.0, -1.0, -50.0, 150.0})  // 150 exceeds $100 balance
    void testInvalidWithdrawals(double amount) {
        assertThrows(InvalidUserInputException.class,
                () -> currentAccount.withdraw(amount));
    }

    // ============ EDGE CASE TESTS ============

    @Nested
    @DisplayName("Edge Case Tests")
    class EdgeCaseTests {

        @Test
        @DisplayName("🟢 Very small amounts should work")
        void testVerySmallAmounts() throws InvalidUserInputException {
            double penny = 0.01;
            double initialBalance = currentAccount.getBalance();

            currentAccount.deposit(penny);
            assertEquals(initialBalance + penny, currentAccount.getBalance(), 0.001);

            currentAccount.withdraw(penny);
            assertEquals(initialBalance, currentAccount.getBalance(), 0.001);
        }

        @Test
        @DisplayName("🟢 Floating point precision should be handled correctly")
        void testFloatingPointPrecision() throws InvalidUserInputException {
            // Test common floating point precision issues
            currentAccount.deposit(0.1);
            currentAccount.deposit(0.2);
            currentAccount.withdraw(0.3);

            // Should not have precision errors that affect functionality
            assertTrue(currentAccount.getBalance() >= 0,
                    "Balance should remain non-negative after floating point operations");
        }
    }
}