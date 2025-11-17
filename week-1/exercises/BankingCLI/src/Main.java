import java.util.Scanner;

public class Main {
    private static final String QUIT_COMMAND = "quit";
    private static final double INITIAL_BALANCE = 0.0;

    private static BankAccount account1;
    private static Scanner scanner;
    private static int userChoice;
    private static final String[] options = {
            "Deposit", "Check Balance", "Withdraw",  "Transaction History", "Set Goal", "Check Progress",
            "Apply Monthly Interest"
    };

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        account1 = new BankAccount(INITIAL_BALANCE);

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

    private static void displayWelcomeMessage() {
        System.out.println(
                "\n##############################\n" +
                        "### WELCOME TO CLI BANKING ###\n" +
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

                    if (userChoice >= 1 && userChoice <= options.length) {
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
                printTransactionHistory();
            case 5:
                setGoal();
            case 6:
                checkProgress();
            case 7:
                selectInterestRate();
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
    }

    private static void checkBalance() {
        double balance = account1.getBalance();
        System.out.println("Your current balance is: " + balance);
    }

    private static void makewithdrawal() {
        double amount;
        System.out.println("How much would you like to withdraw ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            scanner.nextLine();
            if (!account1.withdraw(amount)) {
                System.out.println("You do not have enough funds to withdraw: " + amount);
            } else {
                System.out.println("Success! Your new balance is: " + account1.getBalance());
            }
        } else {
            System.out.println("Invalid amount entered");
        }
    }

    private static void printTransactionHistory() {
        if (!account1.getTransactionHistory()) {
            System.out.println("No transaction history found. Make a deposit / withdrawal!");
        } else {
            System.out.println("\n######## TRANSACTION HISTORY ########\n");
            account1.getTransactionHistory();
            System.out.println("\n#####################################");
        }
    }

    private static void setGoal() {
        double savingsGoal;

        System.out.println("How much would you like to save ?");
        if (scanner.hasNextDouble()) {
            savingsGoal = scanner.nextDouble();
            scanner.nextLine();
            account1.setSavingsGoal(savingsGoal);

            System.out.println("Goal set!");
        } else {
            System.out.println("Please input a valid number!");
        }
    }

    private static void checkProgress() {
        double progress;

        progress = account1.calculateGoalProgress();
        System.out.println("Your goal is: " + account1.getSavingsGoal() + "\nYou are " + progress + "% there!");
    }

    private static void selectInterestRate() {
        System.out.println("What would you like your interest percent rate to be ?");
        if (scanner.hasNextDouble()) {
            double interestRate = scanner.nextDouble();
            scanner.nextLine();
            System.out.println(interestRate + "% interest applied!");
        } else {
            System.out.println("Oops... Something went wrong.");
        }

    }
}