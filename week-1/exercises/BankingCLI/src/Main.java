import bank.BankAccount;
import bank.Goal;
import bank.User;
import bank.exceptions.InvalidUserInputException;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final Double INITIAL_BALANCE = 0.0;
    private static final String[] MENU_OPTIONS = {
            "Deposit",
            "Check Balance",
            "Withdraw",
            "Transaction History",
            "Set Goal",
            "View Goals & Progress",
            "Apply Monthly Interest",
            "View Accounts",
            "Create a new account",
            "Change account name",
            "View account info"
    };

    private static final String[] ACCOUNT_TYPE_OPTIONS = {
            "Standard",
            "Savings"
    };

    private static final double[] INTEREST_RATE_OPTIONS = {
            2.5,
            4.5,
            6.5
    };

    private static User user;
    private static Scanner scanner;
    private static int userChoice;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        user = createUser();

        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            getUserMenuChoice();
            if (userChoice == 0) { // 0 indicates quit
                running = false;
            } else {
                navigateUser();
            }
        }

        scanner.close();
        System.out.println("Thank you for banking with us!");
    }

    static private User createUser() {
        System.out.print("What is your name ?: ");
        if (!scanner.hasNextLine()) {
            return null;
        }
        String name = scanner.nextLine().trim();
        String type = promptForAccountType();
        User newUser = new User(name);

        try {
            newUser.createAccount(INITIAL_BALANCE, type);
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return newUser;
    }

    private static String promptForAccountType() {
        int choice = 0;
        while (true) {
            System.out.println("Choose your account type: ");
            for (int i = 0; i < ACCOUNT_TYPE_OPTIONS.length; i++) {
                System.out.println((i + 1) + ": " + ACCOUNT_TYPE_OPTIONS[i]);
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice == 1) return "STANDARD";
                if (choice == 2) return "SAVINGS";
            } else {
                scanner.nextLine();
            }
            System.out.println("Please enter one of the displayed option numbers!");
        }
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n##############################\n" +
                        " WELCOME TO CLI BANKING " + user.getName().toUpperCase() + "\n" +
                        "##############################"
        );
    }

    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch(NumberFormatException e) {
            return false;
        }
    }

    private static void getUserMenuChoice() {
        boolean validInput = false;
        String inputValue;
        userChoice=0;

        System.out.println("\nMENU OPTIONS:");
        for (int i = 0; i < MENU_OPTIONS.length; i++) {
            System.out.println(i + 1 + ": " + MENU_OPTIONS[i]);
        }

        while (!validInput) {
            System.out.print("\nPlease enter an option number or enter 'quit': ");
            if (scanner.hasNextLine()) {
                inputValue = scanner.nextLine();

                if(isInteger(inputValue)) {
                    userChoice = Integer.parseInt(inputValue);

                    if (userChoice >= 1 && userChoice <= MENU_OPTIONS.length) {
                        validInput = true;
                    } else {
                        System.out.println("\nInvalid input! Please enter one of the option numbers.");
                    }
                } else if(inputValue.equalsIgnoreCase(QUIT_COMMAND)) {
                    break;
                }
                else {
                    System.out.println("\nInvalid input! Please enter one of the option numbers.");
                }
            }
        }
    }

    private static void navigateUser () {
        switch (userChoice) {
            case 1:
                makeDeposit();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                makeWithdrawal();
                break;
            case 4:
                displayTransactionHistory();
                break;
            case 5:
                setGoal();
                break;
            case 6:
                viewGoals();
                break;
            case 7:
                applyInterestRate();
                break;
            case 8:
                viewAccounts();
                break;
            case 9:
                createNewAccount();
                break;
            case 10:
                changeAccountName();
                break;
            case 11:
                viewAccountInfo();
                break;
            default:
                break;
        }
    }

    private static BankAccount currentAccount() {
        return user.getCurrentAccount();
    }

    private static void makeDeposit() {
        System.out.print("How much would you like to deposit?: ");
        String input = scanner.nextLine();

        try {
            double amount = Double.parseDouble(input);
            currentAccount().deposit(amount);
            System.out.println("Success! Your new balance is: " + currentAccount().getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }


    private static void checkBalance() {
        System.out.println("Your current balance is: " + currentAccount().getBalance());
    }

    private static void makeWithdrawal() {
        System.out.println("How much would you like to withdraw ?: ");
        String input = scanner.nextLine();

        try {
            double amount = Double.parseDouble(input);
            currentAccount().withdraw(amount);
            System.out.println("Success! New balance: " + currentAccount().getBalance());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input! Please enter a valid number.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void displayTransactionHistory() {
        if (!currentAccount().getTransactionHistory()) {
            System.out.println("No transaction history found. Make a deposit / withdrawal!");
        } else {
            System.out.println("\n######## TRANSACTION HISTORY ########\n");
            currentAccount().printTransactionHistory();
            System.out.println("\n#####################################");
        }
    }

    private static void applyInterestRate() {
        double rate;

        if (currentAccount().canSetSavingsGoal()) {
            System.out.println(currentAccount().getInterestRateDescription());

            while (true) {
                boolean valid = false;

                System.out.print("Enter an interest rate option: ");

                if (scanner.hasNextDouble()) {
                    rate = scanner.nextDouble();

                    // Check if the rate is one of the allowed options
                    for (double interestRateOption : INTEREST_RATE_OPTIONS) {
                        if (rate == interestRateOption) {
                            currentAccount().setMonthlyInterest(rate);
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


    private static void setGoal() {
        String goalName;
        double savingsGoal;
        LocalDate startDate = LocalDate.now();

        if (currentAccount().canSetSavingsGoal()) {
            System.out.println("Name your goal: ");

            if (scanner.hasNextLine()) {
                goalName = scanner.nextLine();
                System.out.println("How much would you like to save " + user.getName() + "?");

                if (scanner.hasNextDouble()) {
                    savingsGoal = scanner.nextDouble();
                    scanner.nextLine();
                    user.createGoal(goalName, savingsGoal, startDate, currentAccount().getAccountDisplayName());
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

    private static void viewGoals() {
        int i = 1;
        var goals = user.getGoals();

        System.out.println("######## GOALS ########");
        for(Goal goal: goals) {
            System.out.println("Goal " + i + ": " + goal.toString());
            i++;
        }
    }

//    private static void checkProgress() {
//        if (currentAccount().canSetSavingsGoal()) {
//            double progress = currentAccount().calculateGoalProgress();
//            System.out.println("Your goal is: " + currentAccount().getSavingsGoal() +
//                "\nYou are " + progress + "% there " + user.getName() + "!"
//            );
//        } else {
//            System.out.println("No goal available: current account is not a savings account.");
//        }
//    }

    private static void viewAccounts(){
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
        if (!sel.isEmpty() && isInteger(sel)) {
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

    private static void createNewAccount() {
        String type = promptForAccountType();

        try {
            user.createAccount(INITIAL_BALANCE, type);
            System.out.println("Created new " + type + " account and set as current.");
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void changeAccountName() {
        String newAccountName;

        System.out.println("What would you like to name this account?");
            if (scanner.hasNextLine()) {
                newAccountName = scanner.nextLine();
                currentAccount().setAccountName(newAccountName);
                System.out.println("Account name set to: " + newAccountName);
            } else {
                System.out.println("No input found");
            }
    }

    private static void viewAccountInfo() {
        System.out.println(currentAccount().toString());
    }
}