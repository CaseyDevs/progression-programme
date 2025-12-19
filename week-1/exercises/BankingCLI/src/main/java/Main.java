import bank.User;
import bank.exceptions.InvalidUserInputException;
import bank.BankingService;
import helpers.*;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final String[] MENU_OPTIONS = {
            "Deposit",
            "Check Balance",
            "Withdraw",
            "Transaction History",
            "Set Goal",
            "View Goals & Progress",
            "Delete Goal",
            "Apply Monthly Interest",
            "View Accounts",
            "Create a new account",
            "Change account name",
            "View account info",
            "Generate Statement"
    };

    private static User user;
    private static Scanner scanner;
    private static int userChoice;
    private static BankingService bankingService;

    static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        user = createUser();
        bankingService = new BankingService(user);

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
        String type = InputHelpers.promptForAccountType(scanner);
        User newUser = new User(name);
        try {
            newUser.createAccount(0, type);
        } catch (InvalidUserInputException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return newUser;
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n##############################\n" +
                        " WELCOME TO CLI BANKING " + user.getName().toUpperCase() + "\n" +
                        "##############################"
        );
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

                if(helpers.isInteger(inputValue)) {
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

    private static void navigateUser () throws IOException {
        switch (userChoice) {
            case 1:
                bankingService.makeDeposit();
                break;
            case 2:
                bankingService.checkBalance();
                break;
            case 3:
                bankingService.makeWithdrawal();
                break;
            case 4:
                bankingService.displayTransactionHistory();
                break;
            case 5:
                bankingService.setGoal();
                break;
            case 6:
                bankingService.viewGoals();
                break;
            case 7:
                bankingService.deleteGoal();
                break;
            case 8:
                bankingService.applyInterestRate();
                break;
            case 9:
                bankingService.viewAccounts();
                break;
            case 10:
                bankingService.createNewAccount();
                break;
            case 11:
                bankingService.changeAccountName();
                break;
            case 12:
                bankingService.viewAccountInfo();
                break;
            case 13:
                bankingService.generateStatement();
            default:
                break;
        }
    }

 }