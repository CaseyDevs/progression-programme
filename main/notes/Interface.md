
# What is an Interface?
An interface is a blueprint for a class that specifies a set of abstract methods hat implementing classes **MUST** 
define. 

Interfaces support multiple inheritance-like behaviour

Interfaces are very similar to abstract classes, and it many cases where they overlap and either one could be used.

## Key Differences (Interface vs Abstract Class)
1. Supports multiple inheritance-like behaviour - Typically a class can have a single parent, but with interfaces, a
class can have multiple parents. Interfaces can act as contracts, where certain criteria must be met in order for an
implementation of an interface to be valid and usable.

2. Classes can implement multiple interfaces. For example, if we have a preadator and pray interface, fish can be
considered either or both, depending on their breed.

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

public class Fish implements Prey, Predator {
    
    // Both valid as fish implements both interfaces
    @Override
    public void hunt() {
        System.out.println("The hawk is hunting...");
    }

    @Override
    public void flee() {
        System.out.println("The rabbit is running away...");
    }
}

```

# When to use a subclass, abstract class, or interface
- Make a class that doesn’t extend anything(other than Object) when your new class doesn’t
pass the IS-A test for any other type.

- Make a subclass (in other words, extend a class) only when you need to make a more specific
version of a class and need to override or add new behaviors.  

- Use an abstract class when you want to define a template for a group of subclasses, and you 
have at least some implementation code that all subclasses could use. Make the class abstract
when you want to guarantee that nobody can make objects of that type.

- Use an interface when you want to define a role that other classes can play, regardless of where
those classes are in the inheritance tree.