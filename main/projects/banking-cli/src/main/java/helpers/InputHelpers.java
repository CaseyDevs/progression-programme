package helpers;

import bank.User;
import bank.exceptions.InvalidUserInputException;

import java.util.Scanner;
public class InputHelpers {
    private static final String[] ACCOUNT_TYPE_OPTIONS = {
            "Standard",
            "Savings"
    };

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


    public static String promptForAccountType(Scanner scanner) {
        int choice = 0;
        MenuPrinter menuPrinter = new MenuPrinter(ACCOUNT_TYPE_OPTIONS);

        while (true) {
            System.out.println("Choose your account type: ");
            menuPrinter.print();

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

    public static int getUserMenuChoice(Scanner scanner) { // Pass scanner as parameter
        String inputValue;
        int userChoice = 0;
        MenuPrinter menuPrinter = new MenuPrinter(MENU_OPTIONS);

        menuPrinter.print();

        while (true) {
            System.out.print("\nPlease enter an option number or enter 'quit': ");
            if (scanner.hasNextLine()) {
                inputValue = scanner.nextLine();

                if(helpers.isInteger(inputValue)) {
                    userChoice = Integer.parseInt(inputValue);

                    if (userChoice >= 1 && userChoice <= MENU_OPTIONS.length) {
                        return userChoice;
                    } else {
                        System.out.println("\nInvalid input! Please enter one of the option numbers.");
                    }
                } else if(inputValue.equalsIgnoreCase(QUIT_COMMAND)) {
                    return 0; // Return 0 to indicate quit instead of breaking
                }
                else {
                    System.out.println("\nInvalid input! Please enter one of the option numbers.");
                }
            }
        }
    }

    public static User createUser(Scanner scanner) { // Pass scanner as parameter
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
        
        // Don't close the scanner here!
        return newUser;
    }
}
