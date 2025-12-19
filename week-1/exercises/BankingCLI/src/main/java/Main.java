import bank.User;
import bank.exceptions.InvalidUserInputException;
import bank.BankingService;
import helpers.*;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    private static User user;
    private static Scanner scanner;
    private static BankingService bankingService;

    public static void main(String[] args) throws IOException { // Fix: should be public
        scanner = new Scanner(System.in); // Create scanner first
        user = InputHelpers.createUser(scanner); // Pass scanner to createUser
        bankingService = new BankingService(user);

        displayWelcomeMessage();

        boolean running = true;
        while (running) {
            int userChoice = InputHelpers.getUserMenuChoice(scanner); // Pass scanner
            if (userChoice == 0) { // 0 indicates quit
                running = false;
            } else {
                navigateUser(userChoice);
            }
        }

        scanner.close(); // Only close at the very end
        System.out.println("Thank you for banking with us!");
    }

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n##############################\n" +
                        " WELCOME TO CLI BANKING " + user.getName().toUpperCase() + "\n" +
                        "##############################"
        );
    }


    private static void navigateUser (int userChoice) throws IOException {
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