import bank.BankAccount;
import bank.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final double INITIAL_BALANCE = 0.0;
    private static final String[] MENU_OPTIONS = {
            "Deposit",
            "Check Balance",
            "Withdraw",
            "Transaction History",
            "Set Goal",
            "Check Progress",
            "Apply Monthly Interest",
            "View Accounts",
            "Create a new account"
    };

    private static final String[] ACCOUNT_TYPE_OPTIONS = {
            "Current",
            "Savings"
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
        newUser.createAccount(INITIAL_BALANCE, type);
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
                if (choice == 1) return "current";
                if (choice == 2) return "savings";
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

        System.out.println("\nMENU_OPTIONS:");
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
                makewithdrawal();
                break;
            case 4:
                displayTransactionHistory();
                break;
            case 5:
                setGoal();
                break;
            case 6:
                checkProgress();
                break;
            case 7:
                selectInterestRate();
                break;
            case 8:
                viewAccounts();
                break;
            case 9:
                createNewAccount();
                break;
            default:
                break;
        }
    }

    private static BankAccount activeAccount() {
        return user.getActiveAccount();
    }

    private static void makeDeposit() {
        System.out.println("How much would you like to deposit ?: ");
        if(scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            activeAccount().deposit(amount);
            System.out.println("Success! Your new balance is: " + activeAccount().getBalance());
        } else {
            System.out.println("Invalid amount entered");
            scanner.nextLine();
        }
    }

    private static void checkBalance() {
        System.out.println("Your current balance is: " + activeAccount().getBalance());
    }

    private static void makewithdrawal() {
        System.out.println("How much would you like to withdraw ?: ");
        if(scanner.hasNextDouble()) {
            double amount = scanner.nextDouble();
            scanner.nextLine();
            if (!activeAccount().withdraw(amount)) {
                System.out.println("You do not have enough funds to withdraw: " + amount);
            } else {
                System.out.println("Success! Your new balance is: " + activeAccount().getBalance());
            }
        } else {
            System.out.println("Invalid amount entered");
            scanner.nextLine();
        }
    }

    private static void displayTransactionHistory() {
        if (!activeAccount().getTransactionHistory()) {
            System.out.println("No transaction history found. Make a deposit / withdrawal!");
        } else {
            System.out.println("\n######## TRANSACTION HISTORY ########\n");
            activeAccount().printTransactionHistory();
            System.out.println("\n#####################################");
        }
    }

    private static void setGoal() {
        if(activeAccount().getAccountType().equals("savings")) {
            System.out.println("How much would you like to save " + user.getName() + "?");
            if (scanner.hasNextDouble()) {
                double savingsGoal = scanner.nextDouble();
                scanner.nextLine();
                activeAccount().setSavingsGoal(savingsGoal);
                System.out.println("Goal set!");
            } else {
                System.out.println("Please input a valid number!");
                scanner.nextLine();
            }
        } else {
            System.out.println("You can only set a savings goal using a savings account!");
        }
    }

    private static void checkProgress() {
        double progress = activeAccount().calculateGoalProgress();
        System.out.println("Your goal is: " + activeAccount().getSavingsGoal() +
                "\nYou are " + progress + "% there " + user.getName() + "!"
        );
    }

    private static void selectInterestRate() {
        System.out.println(user.getName() + " what would you like your interest percent rate to be ?");
        if (scanner.hasNextDouble()) {
            double interestRate = scanner.nextDouble();
            scanner.nextLine();

            activeAccount().setMonthlyInterest(interestRate);
            System.out.println(interestRate + "% interest applied!");
        } else {
            System.out.println("Oops... Something went wrong.");
            scanner.nextLine();
        }
    }

    private static void viewAccounts(){
        List<BankAccount> list = user.getAccountList();
        if (list.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("Your accounts:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ": " + list.get(i).getAccountType());
        }
        System.out.print("Select an account number to make it current (or press Enter to keep current): ");
        String sel = scanner.nextLine();
        if (!sel.isEmpty() && isInteger(sel)) {
            int idx = Integer.parseInt(sel) - 1;
            try {
                user.setActiveAccount(idx);
                System.out.println("Switched to " + user.getActiveAccount().getAccountType() + " account.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid selection.");
            }
        }
    }

    private static void createNewAccount() {
        String type = promptForAccountType();
        user.createAccount(INITIAL_BALANCE, type);
        System.out.println("Created new " + type + " account and set as current.");
    }
}