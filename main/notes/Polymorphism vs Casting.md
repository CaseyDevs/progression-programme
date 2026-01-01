# Polymorphism vs Casting (Reference Types)

## What decides which method runs at runtime ?
- ***Polymorphism*** - The actual object type decides.
- ***Casting*** - Casting does not decide runtime behaviour; it only affects what the compiler allows us to call.

## Key differences 
- Casting is manual and explicit.
- Casting is used to access subclass-specific methods.
- Casting often requires `instanceof` and can throw `ClassCastException`.
- Polymophism is automatic and works via method overriding - removing the need of casts and conditionals.

### Memory hook
Polymorphism chooses behavior; casting only changes access.

### Example of casting and conditionals (poor)
```java
if (account instanceof SavingsAccount) {
    ((SavingsAccount) account).monthlyUpdate();
} else if (account instanceof CurrentAccount) {
    ((CurrentAccount) account).monthlyUpdate();
}
```

### Example of polymorphism
```java
BankAccount account = getAccount();
account.monthlyUpdate(); // correct version chosen at runtime
```