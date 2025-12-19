package helpers;

import java.util.Scanner;

public class InputHelpers {
    private static final String[] ACCOUNT_TYPE_OPTIONS = {
            "Standard",
            "Savings"
    };

    public static String promptForAccountType(Scanner scanner) {
        int choice = 0;
        while (true) {
            System.out.println("Choose your account type: ");
            for (int i = 0; i < ACCOUNT_TYPE_OPTIONS.length; i++) {
                System.out.println((i + 1) + ": " + ACCOUNT_TYPE_OPTIONS[i]);
            }
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
}
