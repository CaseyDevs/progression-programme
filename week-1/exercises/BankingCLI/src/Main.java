import java.util.Scanner;

public class Main {
    private static BankAccount account1;
    private static Scanner scanner;

    public static void main (String[] args) {
        scanner = new Scanner(System.in);
        boolean validInput = false;
        int userChoice = 0;

        String[] options = {"Deposit", "Check Balance", "Withdraw"};
        account1 = new BankAccount(1000);

        System.out.println(
                "\n##############################\n" +
                "### WELCOME TO CLI BANKING ###\n" +
                "##############################\n"
        );

        // Output options
        System.out.println("Options:");
        for(int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ": " + options[i]);
        }

        // Gather and validate user input
        while (!validInput) {
            System.out.print("\nPlease enter an option number: ");
            if (scanner.hasNextInt()) { // Check is input value is of type int
                userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= options.length + 1) { // Ensure input is a valid option
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter one of the option numbers.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }


        switch(userChoice) {
            case 1:
                makeDeposit();
                break;
            case 2:
                checkBalance();
                break;
            case 3:
                makeWidthdrawal();
                break;
            default:
                break;
        }

        scanner.close();
    }

    private static void makeDeposit() {
        double amount;

        System.out.println("How much would you like to deposit ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            account1.deposit(amount);
            System.out.println("Success! Your new balance is: " + account1.getBalance());
        } else {
            System.out.println("Invalid amount entered");
        }
    }

    private static void checkBalance() {
        double balance = account1.getBalance();
        System.out.println("Your current balance is: " + balance);
    }

    private static void makeWidthdrawal() {
        double amount;
        System.out.println("How much would you like to widthdraw ?: ");
        if(scanner.hasNextDouble()) {
            amount = scanner.nextDouble();
            if (!account1.widthdraw(amount)) {
                System.out.println("You do not have enough funds to widthdraw: " + amount);
            } else {
                System.out.println("Success! Your new balance is: " + account1.getBalance());
            }
        } else {
            System.out.println("Invalid amount entered");
        }
    }
}