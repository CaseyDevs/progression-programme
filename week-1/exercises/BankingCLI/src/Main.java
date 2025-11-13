import java.util.Scanner;

public class Main {
    private static BankAccount account1;
    private static Scanner scanner;
    private static int userChoice;
    private static String[] options = {"Deposit", "Check Balance", "Withdraw"};

    public static void main (String[] args) {
        scanner = new Scanner(System.in);
        userChoice = 0;
        account1 = new BankAccount(1000);

        System.out.println(
                "\n##############################\n" +
                        "### WELCOME TO CLI BANKING ###\n" +
                        "##############################"
        );

        getUserChoice();
        scanner.close();
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
        String inputValue = "";
        userChoice=0;

        // Output options
        System.out.println("\nOptions:");
        for (int i = 0; i < options.length; i++) {
            System.out.println(i + 1 + ": " + options[i]);
        }

        // Gather and validate user input
        while (!validInput) {
            System.out.print("\nPlease enter an option number or enter 'quit': ");
            if (scanner.hasNextLine()) { // Check is input value is of type int
                inputValue = scanner.nextLine();

                // Check if input can be parsed to intger
                if(isInteger(inputValue)) {
                    userChoice = Integer.parseInt(inputValue);

                    if (userChoice >= 1 && userChoice <= options.length) { // Fixed: removed + 1
                        validInput = true;
                    } else {
                        System.out.println("\nInvalid input! Please enter one of the option numbers.");
                    }
                // Check if input is "quit"
                } else if(inputValue.equalsIgnoreCase("quit")) {
                    break;
                }
                else {
                    System.out.println("\nInvalid input! Please enter one of the option numbers.");
                }
            }
        }
        navigateUser();
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
            account1.deposit(amount);
            System.out.println("Success! Your new balance is: " + account1.getBalance());
        } else {
            System.out.println("Invalid amount entered");
            scanner.nextLine();
        }

        getUserChoice();
    }


    private static void checkBalance() {
        double balance = account1.getBalance();
        System.out.println("Your current balance is: " + balance);

        getUserChoice();
    }


    private static void makewithdrawal() {
        double amount;
        System.out.println("How much would you like to withdraw ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            if (!account1.withdraw(amount)) {
                System.out.println("You do not have enough funds to withdraw: " + amount);
            } else {
                System.out.println("Success! Your new balance is: " + account1.getBalance());
            }
        } else {
            System.out.println("Invalid amount entered");
        }

        getUserChoice();
    }
}