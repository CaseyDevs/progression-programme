public class BankAccount {
    private double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public boolean widthdraw(double amount) {
        if (balance - amount < 0) {
            return false;
            // System.out.println("You do not have enough funds to widthdraw: " + amount);
        } else {
            balance -= amount;
            return true;
            // System.out.println("Success! Your new balance is: " + balance);
        }
    }
}
