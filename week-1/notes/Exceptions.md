# What are exceptions ?
Exceptions represent different types of errors that are thrown during a programs execution, whether at compile or 
runtime. Exceptions can be split into 2 types in Java, `checked` and `unchecked` exceptions.

# Checked exceptions
Checked exceptions are handled at compile time, where the compiler forces us to catch them, or declare them with the
`throws` keyword. They represent recoverable, expected problems.

***Common Checked Exceptions***:
`IOException`
`SQLException`
`FileNotFoundException`

**When to use checked exceptions:**
- External systems (Files, Networks, Database)
- Situations where caller can realistically recover
- Low-level API libraries

## Checked Exception Example:
```java
public void readFile() throws IOException {
    FileReader reader = new FileReader("data.txt");
}
```
or handled:
```java
public void readFile() {
    try {
        FileReader reader = new FileReader("data.txt");
    } catch (IOException e) {
        System.out.println("File could not be read");
    }
}
```

## Unchecked exception (Runtime exceptions)
The compiler does not enforce unchecked exceptions; they extend `RuntimeException`. Unchecked exceptions do not have
to be caught or declared; they represent programming errors or invalid state.

If a unchecked exception happens without being caught, the program will fail at runtime.

***Common unchecked exceptions:***
`NullPointerException`
`IllegalArgumentException`
`IllegalStateException`
`IndexOutOfBoundsException`

### Checked Exception Example:
```java
public void withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("Amount must be positive");
    }
}
```

## Why Spring prefers unchecked exceptions
- Cleaner method signatures – unchecked exceptions don’t force throws everywhere, keeping service and controller APIs readable.
- Better for layered architectures – exceptions can bubble up without leaking low-level details (e.g. SQL) through every layer.
- Centralised error handling – Spring expects runtime exceptions to be handled globally (@ControllerAdvice).
- Works naturally with transactions – Spring rolls back transactions by default on unchecked exceptions.
- Framework consistency – Spring itself wraps checked exceptions (e.g. SQLException) into unchecked ones.