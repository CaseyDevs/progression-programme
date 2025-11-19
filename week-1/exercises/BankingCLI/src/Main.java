import bank.BankAccount;
import bank.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final double INITIAL_BALANCE = 0.0;
    private static final String[] OPTIONS = {
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

    private static User user;
    private static BankAccount account;
    private static Scanner scanner;
    private static int userChoice;
    private static final List<BankAccount> accounts = new ArrayList<>();


    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        user = createUser();

        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            getUserChoice();
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
        if (scanner.hasNextLine()) {
            String name = scanner.nextLine();

            System.out.print("Choose your account type: ");
            if (scanner.hasNextLine()) {
                String type = scanner.nextLine();
                account = new BankAccount(INITIAL_BALANCE, user, type);
                accounts.add(account);
                user = new User(name, accounts);
            }


            return user;
        } else {
            return null;
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

    private static void getUserChoice() {
        boolean validInput = false;
        String inputValue;
        userChoice=0;

        // Output options
        System.out.println("\nOPTIONS:");
        for (int i = 0; i < OPTIONS.length; i++) {
            System.out.println(i + 1 + ": " + OPTIONS[i]);
        }

        // Gather and validate user input
        while (!validInput) {
            System.out.print("\nPlease enter an option number or enter 'quit': ");
            if (scanner.hasNextLine()) { // Check is input value is of type int
                inputValue = scanner.nextLine();

                // Check if input can be parsed to integer
                if(isInteger(inputValue)) {
                    userChoice = Integer.parseInt(inputValue);

                    if (userChoice >= 1 && userChoice <= OPTIONS.length) {
                        validInput = true;
                    } else {
                        System.out.println("\nInvalid input! Please enter one of the option numbers.");
                    }
                // Check if input is "quit"
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

    private static void makeDeposit() {
        double amount;

        System.out.println("How much would you like to deposit ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            scanner.nextLine();
            account.deposit(amount);
            System.out.println("Success! Your new balance is: " + account.getBalance());
        } else {
            System.out.println("Invalid amount entered");
            scanner.nextLine();
        }
    }

    private static void checkBalance() {
        double balance = account.getBalance();
        System.out.println("Your current balance is: " + balance);
    }

    private static void makewithdrawal() {
        double amount;
        System.out.println("How much would you like to withdraw ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            scanner.nextLine();
            if (!account.withdraw(amount)) {
                System.out.println("You do not have enough funds to withdraw: " + amount);
            } else {
                System.out.println("Success! Your new balance is: " + account.getBalance());
            }
        } else {
            System.out.println("Invalid amount entered");
        }
    }

    private static void displayTransactionHistory() {
        if (!account.getTransactionHistory()) {
            System.out.println("No transaction history found. Make a deposit / withdrawal!");
        } else {
            System.out.println("\n######## TRANSACTION HISTORY ########\n");
            account.printTransactionHistory();
            System.out.println("\n#####################################");
        }
    }

    private static void setGoal() {
        double savingsGoal;

        System.out.println("How much would you like to save " + user.getName() + "?");
        if (scanner.hasNextDouble()) {
            savingsGoal = scanner.nextDouble();
            scanner.nextLine();
            account.setSavingsGoal(savingsGoal);

            System.out.println("Goal set!");
        } else {
            System.out.println("Please input a valid number!");
            scanner.nextLine();
        }
    }

    private static void checkProgress() {
        double progress;

        progress = account.calculateGoalProgress();
        System.out.println("Your goal is: " + account.getSavingsGoal() +
                "\nYou are " + progress + "% there " + user.getName() + "!"
        );
    }

    private static void selectInterestRate() {
        System.out.println(user.getName() + "what would you like your interest percent rate to be ?");
        if (scanner.hasNextDouble()) {
            double interestRate = scanner.nextDouble();
            scanner.nextLine();

            account.setMonthlyInterest(interestRate);
            System.out.println(interestRate + "% interest applied!");
        } else {
            System.out.println("Oops... Something went wrong.");
            scanner.nextLine();
        }

    }

    private static void viewAccounts(){
        for (BankAccount account: user.getAccountList()) {
            System.out.println(account.getAccountType());
        }
    }

    private static void createNewAccount() {
        String accountType;

        System.out.println("Choose and account type: ");
        if (scanner.hasNextLine()) {
            accountType = scanner.nextLine();
            System.out.println("Perfect! Creating a " + accountType + " account");

            BankAccount newAccount = new BankAccount(INITIAL_BALANCE, user, accountType);
            accounts.add(newAccount);
            user.addAccount(newAccount);
        }
    }

}