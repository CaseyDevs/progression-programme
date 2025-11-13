import java.util.Scanner;

public class Main {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean validInput = false;

        String[] options = {"Deposit", "Check Balance", "Withdraw"};
        BankAccount account1 = new BankAccount(1000);

        System.out.println(
                "##############################\n" +
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
            System.out.println("Please enter an option number: ");
            if (scanner.hasNextInt()) { // Check is input value is of type int
                int userChoice = scanner.nextInt();
                if (userChoice >= 1 && userChoice <= options.length) { // Ensure input is a valid option
                    validInput = true;
                } else {
                    System.out.println("Invalid input! Please enter one of the option numbers.");
                    scanner.next(); // Clear the invalid input
                }
            }
        }

    }
}