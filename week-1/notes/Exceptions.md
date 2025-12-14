# What are exceptions ?
Exceptions represent different types of errors that are thrown during a programs execution, whether at compile or 
runtime. Exceptions can be split into 2 types in Java, `checked` and `unchecked` exceptions.

# Checked exceptions
Checked exceptions are thrown at compile time and typically relate to errors outside the programs control, such as
invalid user input. Checked exceptions are typically accompanied by the `throws` keyword in Java and 'check' code
contained within `try-catch` blocks.

## Checked Exception Example:

```java
import java.util.scanner;

public class Main {
    void main() {
        int num;
        Scanner scanner = new Scanner(System.in);
        
        try {
            System.out.println("Enter a number: ");
            if(scanner.hasNextLine()) {
                num = Integer.parseInt(scanner); 
                System.out.println("Success! " + num + " is a number");
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

## Unchecked exception
Unchecked exceptions reflect some type of error in a programs logic, for example if we try to divide a number by 0 in
Java, we will get an `ArithmeticExcpetion`. 


```java
private static void divideByZero() {
    int numerator = 1;
    int denominator = 0;
    int result = numerator / denominator;
}
```