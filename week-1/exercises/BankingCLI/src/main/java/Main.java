
import bank.User;
import bank.BankingService;
import helpers.*;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static User user;
    private static Scanner scanner;
    private static BankingService bankingService;

    public static void main(String[] args) {
        try {
            initializeApplication();
            runApplication();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
        } finally {
            cleanup();
        }
    }

    private static void initializeApplication() {
        scanner = new Scanner(System.in);

        // Handle user creation with retry logic
        while (user == null) {
            try {
                user = InputHelpers.createUser(scanner);
                if (user == null) {
                    System.out.println("Failed to create user. Please try again.");
                    continue;
                }
                bankingService = new BankingService(user);
                displayWelcomeMessage();
                break;
            } catch (Exception e) {
                System.err.println("Error creating user: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    private static void runApplication() {
        boolean running = true;
        while (running) {
            try {
                int userChoice = InputHelpers.getUserMenuChoice(scanner);
                if (userChoice == 0) { // 0 indicates quit
                    running = false;
                } else {
                    navigateUser(userChoice);
                }
            } catch (Exception e) {
                System.err.println("Error processing menu choice: " + e.getMessage());
                System.out.println("Please try again.");
            }
        }
    }

    private static void cleanup() {
        if (scanner != null) {
            scanner.close();
        }
        System.out.println("Thank you for banking with us!");
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n##############################\n" +
                        " WELCOME TO CLI BANKING " + user.getName().toUpperCase() + "\n" +
                        "##############################"
        );
    }

    private static void navigateUser(int userChoice) {
        try {
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
                    break;
                default:
                    System.out.println("Invalid menu option.");
                    break;
            }
        } catch (IOException e) {
            System.err.println("File operation error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Operation failed: " + e.getMessage());
        }
    }
}