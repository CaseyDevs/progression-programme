public class Main {
    public static void main (String[] args) {

        String[] options = {"Deposit", "Check Balance", "Withdraw"};
        BankAccount account1 = new BankAccount(1000);

        System.out.println(
                "##############################\n" +
                "### WELCOME TO CLI BANKING ###\n" +
                "##############################\n"
        );

        System.out.println("Choose an option: ");

        for(int i = 0; i < options.length; i++) {
            System.out.println(i+1 + ": " + options[i]);
        }

        // TODO:
        //  ADD WITHDRAW METHOD,
        //  CLI MENU USING SCANNER,
        //  IMPROVE INPUT VALIDATION
    }
}