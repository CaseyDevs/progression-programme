public class Main {
    public static void main (String[] args) {
        BankAccount account1 = new BankAccount(1000);

        System.out.println("Current Balance" + account1.getBalance());
        account1.deposit(500);
    }
}