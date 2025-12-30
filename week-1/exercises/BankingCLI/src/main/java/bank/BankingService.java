package bank;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import dto.AccountStatementDTO;
import dto.GoalDTO;
import bank.exceptions.InvalidUserInputException;
import bank.generators.*;
import helpers.*;

public class BankingService {
    private final User user;

    private static final Double INITIAL_BALANCE = 0.0;
    private static final double[] INTEREST_RATE_OPTIONS = {
            2.5,
            4.5,
            6.5
    };

    public BankingService(User user) {
        this.user = user;
    }

    private BankAccount currentAccount() {
        return user.getCurrentAccount();
    }

    public void makeDeposit(Scanner scanner) {
        BankAccount account = currentAccount();
        System.out.print("How much would you like to deposit?: ");
        String input = scanner.nextLine();

        try {
            double amount = Double.parseDouble(input);
            account.deposit(amount);
            System.out.println("Success! Your new balance is: " + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void makeWithdrawal(Scanner scanner) {
        BankAccount account = currentAccount();
        System.out.println("How much would you like to withdraw ?: ");
        String input = scanner.nextLine();

        try {
            double amount = Double.parseDouble(input);
            account.withdraw(amount);
            System.out.println("Success! New balance: " + account.getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void checkBalance() {
        BankAccount account = currentAccount();
        System.out.println("Your current balance is: " + account.getBalance());
    }

    public void displayTransactionHistory() {
        BankAccount account = currentAccount();
        if (!account.getTransactionHistory()) {
            System.out.println("No transaction history found. Make a deposit / withdrawal!");
        } else {
            System.out.println("\n######## TRANSACTION HISTORY ########\n");
            account.printTransactionHistory();
            System.out.println("\n#####################################");
        }
    }

    public void applyInterestRate(Scanner scanner) {
        BankAccount account = currentAccount();
        double rate;

        if (account instanceof SavingsCapable savings) {
            System.out.println(account.getInterestRateDescription());

            while (true) {
                boolean valid = false;

                System.out.print("Enter an interest rate option: ");

                if (scanner.hasNextDouble()) {
                    rate = scanner.nextDouble();

                    // Check if the rate is one of the allowed options
                    for (double interestRateOption : INTEREST_RATE_OPTIONS) {
                        if (rate == interestRateOption) {
                            savings.setMonthlyInterest(rate);
                            valid = true;
                            break;
                        }
                    }

                    if (valid) {
                        System.out.println("Interest rate applied.");
                        break;
                    } else {
                        System.out.println("Invalid rate. Please choose one of the available options.");
                    }

                } else {
                    System.out.println("Please enter a valid number.");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("This account type cannot modify its savings goal or interest rate.");
        }

        scanner.nextLine();
    }

    public void viewGoals() {
        int i = 1;
        var goals = user.getGoals();
        BankAccount account = currentAccount();


        if (account instanceof SavingsCapable savings && !goals.isEmpty()) {
            System.out.println("######## GOALS ########");
            for (Goal goal : goals) {
                double progress = savings.calculateGoalProgress(goal.getGoalTarget());
                System.out.println("\nGoal " + i + ": " + goal.toString() + "\n- Progress: " + Math.round(progress) + "%");
                i++;
            }
        } else {
            System.out.println("You have not set any goals yet.");
        }
    }

    public void setGoal(Scanner scanner) {
        String goalName;
        double savingsGoal;
        BankAccount account = currentAccount();

        if (account instanceof SavingsCapable savings) {
            System.out.println("Name your goal: ");

            if (scanner.hasNextLine()) {
                goalName = scanner.nextLine();
                System.out.println("How much would you like to save " + user.getName() + "?");

                if (scanner.hasNextDouble()) {
                    savingsGoal = scanner.nextDouble();
                    scanner.nextLine();
                    savings.setSavingsGoal(goalName, savingsGoal);
                    System.out.println("Goal Created");
                } else {
                    System.out.println("Please input a valid number!");
                    scanner.nextLine();
                }
            }
        } else {
            System.out.println("You can only set a savings goal using a savings account!");
        }
    }

    public void deleteGoal(Scanner scanner) {
        var goals = user.getGoals();
        BankAccount account = currentAccount();

        if (account instanceof SavingsCapable) {
            if (goals.isEmpty()) {
                System.out.println("No goals to delete.");
                return;
            }

            System.out.println("Which goal would you like to remove:");
            for (int i = 0; i < goals.size(); i++) {
                System.out.println((i + 1) + ": " + goals.get(i).getGoalName());
            }

            while (true) {
                System.out.print("Enter a number: ");

                if (!scanner.hasNextInt()) {
                    System.out.println("Please enter a whole number!");
                    scanner.next(); // consume invalid input
                    continue;
                }

                int choice = scanner.nextInt();

                if (choice < 1 || choice > goals.size()) {
                    System.out.println("Error! Please choose a valid option.");
                    continue;
                }

                // delete goal
                user.deleteGoal(goals.get(choice - 1));
                System.out.println("Goal removed.");
                break;
            }
        } else {
            System.out.println("You can only create and delete goals in savings accounts!");
        }

        scanner.nextLine(); // clear newline
    }

    public void viewAccounts(Scanner scanner){
        List<BankAccount> list = user.getAccountList();
        if (list.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("Your accounts:");
        for (int i = 0; i < list.size(); i++) {
            // Use the new polymorphic method instead of getAccountType()
            System.out.println((i + 1) + ": "
                    + list.get(i).getAccountDisplayName() + " ("
                    + currentAccount().getAccountType() + ")");
        }
        System.out.print("Select an account number to make it current (or press Enter to keep current): ");
        String sel = scanner.nextLine();

        if (!sel.isEmpty() && helpers.isInteger(sel)) {
            int idx = Integer.parseInt(sel) - 1;
            try {
                user.setCurrentAccount(idx);
                System.out.println("Switched to "
                        + currentAccount().getAccountDisplayName()
                        + "(" + currentAccount().getAccountType() + ")"
                );
            } catch (IndexOutOfBoundsException e){
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    public void createNewAccount(Scanner scanner) {
        String type = InputHelpers.promptForAccountType(scanner);

        try {
            user.createAccount(INITIAL_BALANCE, type);
            System.out.println("Created new " + type + " account and set as current.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void changeAccountName(Scanner scanner) {
        BankAccount account = currentAccount();
        String newAccountName;

        System.out.println("What would you like to name this account?");
        if (scanner.hasNextLine()) {
            newAccountName = scanner.nextLine();
            account.setAccountName(newAccountName);
            System.out.println("Account name set to: " + newAccountName);
        } else {
            System.out.println("No input found");
        }
    }

    public void viewAccountInfo() {
        BankAccount account = currentAccount();
        System.out.println(account.toString());
    }

    private AccountStatementDTO mapToAccountStatementDTO(BankAccount account) {
        List<GoalDTO> goalDTOs = user.getGoals().stream()
                .map(goal -> new GoalDTO (
                        goal.getGoalName(),
                        goal.getGoalTarget(),
                        goal.getStartDate()
                ))
                .toList();

        return new AccountStatementDTO(
                account.getAccountDisplayName(),
                account.getAccountType(),
                account.getBalance(),
                List.of(account.getTransactionHistoryAsString().split("\n")),
                goalDTOs,
                user.getName(),
                account.getInterestRateDescription()
        );
    }

    public void generateStatement(Scanner scanner) {
        int choice;
        boolean running = true;

        System.out.println("Type '1' for text, '2' for JSON, '3' for CSV");

        while (running) {
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        try {
                            TextStatementGenerator textStatementGenerator = new TextStatementGenerator();
                            textStatementGenerator.generator(currentAccount());
                            System.out.println("Success, check your directory for a statement.");
                            running = false;
                        } catch (Exception e) {
                            System.out.println("Error generating text statement");
                        }
                        break;

                    case 2:
                        try {
                            JSONStatementGenerator jsonStatementGenerator = new JSONStatementGenerator();
                            jsonStatementGenerator.generator(currentAccount());
                            System.out.println("Success, check your directory for a statement.");
                            running = false;
                        } catch (Exception e) {
                            System.out.println("Error generating text statement");
                        }
                        break;
                    case 3:
                        try {
                            CSVStatementGenerator csvStatementGenerator = new CSVStatementGenerator();
                            csvStatementGenerator.generator(currentAccount());
                            System.out.println("Success, check your directory for a statement.");
                            running = false;
                        } catch (RuntimeException e) {
                            System.out.println("Error generating text statement");
                        }
                        break;
                    default:
                        System.out.println("Invalid option, try again!");
                }

            } else {
                scanner.next();
            }
        }
    }
}
