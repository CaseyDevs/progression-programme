# Definition & Usage

Constructors are special methods in OOP that are automatically called when an object is created to initialise it.
Constructors often assign values to attributes (fields) that are specific to each object (instance variales), as
well as attributes that are shared across all objects (class/static variables).

For example, a `BankAccount` constructor might initialise an `accountNumber` (unique to each object) while all bank 
accounts share a `bankName`. Object-specific fields are initialised using the `this` keyword.

***Example:***
```java
class BankAccount {
    private int accountNumber; // Object-specific (instance variable)
    private static String bankName; // class variable (universal)
    
    public BankAccount(int accountNumber) { // Constructor
       this.accountNumber = accountNumber; 
    }
}
```

# Overriding Constructors
Sometimes, a class may need multiple constructors with different parameters. This is called **constructor overloading**.
For example, you might want to create an account either with a username and password or as a guest account with default values.

```java
class Account {
    private String username;
    private String password;
    
    // Default constructor
    public Account(String username,String password) {
        this.username = username;
        this.password = password;
    }

    // Overriding constructor for guest accounts
    public Account() { 
        this.username = "Guest";
        this.password = null; // Here for readability
    }
}
```