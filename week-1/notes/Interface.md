
# What is an Interface?
An interface is a blueprint for a class that specifies a set of abstract methods hat implementing classes **MUST** 
define. 

Interfaces support multiple inheritance-like behaviour

Interfaces are very similar to abstract classes, and it many cases where they overlap and either one could be used.

## Key Differences (Interface vs Abstract Class)
1. Supports multiple inheritance-like behaviour - Typically a class can have a single parent, but with interfaces, a
class can have multiple parents. Interfaces can act as contracts, where certain criteria must be met in order for an
implementation of an interface to be valid and useable.

## Creating an Interface
`File -> New -> Interface`

```java
public interface Prey {
    void flee();
}

public interface Predator {
    void hunt();
}
```

```java
public class Rabbit implements Prey {
    
    // Interface methods must be used, or, Rabbit must be abstract
    @Override
    public void flee() {
        System.out.println("The rabbit is running away...");
    }
}

public class Hawk implements Predator {
    @Override
    public void hunt() {
        System.out.println("The hawk is hunting...");
    }
}

```