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
        System.out.println("Deposit of £" + amount + " successful!");
        System.out.println("New balance: " + this.balance);
    }

    public void widthdraw(double amount) {
        if (balance - amount < 0) {
            System.out.println("You do not have enough funds to widthdraw: " + amount);
        } else {
            balance -= amount;
            System.out.println("Success! Your new balance is: " + balance);
        }
    }
}
