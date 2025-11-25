# The 4 OOP Principles

## Abstraction

## Encapsulation

## Inheritance
Inheritance is a principle that allows child classes to inherit methods from parent classes. This allows us to implement
the same behaviours of a parent class, but extend on them to making the child class unique.

In Java, to implement inheritance, we use the `extends` keyword, indicating that a child class is an extension of a 
a parent class.

### Inheritance Example:
```java
public class Animal {
    boolean isAlive;
    
    Animal() {
        isAlive = true;                
    } 
    
    public void walk() {
        System.out.println("Walking...");
    }
}


public class Lion extends Animal {
    int lives = 1;
    
    public void speak() {
        System.out.println("ROARRR");
    }   
}


public class Dog extends Animal {
    int lives = 2;

    public void speak() {
        System.out.println("Woof Woof!");
    }
}

public class Main {
    public static void Main(string[] args) {
        Dog dog = new Dog();
        Lion lion = new Lion();
        
        dog.walk(); // Walking...
        lion.walk(); // Walking...
        
        dog.speak(); // Woof Woof!
        lion.speak(); // ROARRR
    }
}
```

### The Super Keyword:
The super keyword refers to the parent class and is typically used in constructors and ovveriding. It calls the parent 
constructor to initialise attributes.

```java
public class Person {
    
    String first;
    String last;
    
    Person(String first, String last) {
        this.first = first;
        this.last = last;
    }
    
    void showName() {
        System.out.println(this.first + " " + this.last);
    }
}


public class Student extends Person {
    double gpa;
    
    Student(String first, String last, double gpa) {
        super(first, last); // Allows us to assign first and last name.
        
        this.gpa = gpa;
    }
    
    void showGpa () {
        Sytem.out.println(this.first + "'s GPA is: " + gpa);
    }
}
```

## Polymorphism

Polymorphism is a principle that allows sub-classes to be treated as instances of their parent class, supporting
flexibility and providing the ability to define methods in multiple forms, primarily achieved through overlridding and
overloading. 

"Polymorphism is used to perform a single action in different ways. It allows for the implementation of dynamic method 
dispatch, where the method to be executed is determined at runtime. This is beneficial for implementing a cleaner and 
more maintainable codebase."

When you declare a reference variable, any object that passes the IS-A test for the declared type of the reference 
variable can be assigned to that reference. This lets you do things like make polymorphic arrays.

### Example
```java
Animal[] animals = new Animal[5];
animals[0] = new Dog();
animals[1] = new Cat();

for (int i = 0; i <= animals.length; i++) {
    animals[i].eat();
        }

```

In the given example, the Dog and Cat classes inherit Animal. Despite not being of type animal, their IS-A relationship
allows them to be added to the Animal type array. This behaviour is an example of polymorphism.

### Types of Polymorphism
1. Compile-time Polymorphism (Method Overloading): Achieved by defining multiple methods with the same name but
different parameters within the same class.

2. Runtime Polymorphism (Method Overriding): Achieved when a subclass provides a specific implementation of a method
already defined in its superclass.



# Overriding & Overloading

## What is Overriding ?
Overriding refers to a subclass providing its own implementation of a method that is already defined by a parent
class.  This enables code reusability, but also specific implementations. Let's say a parent method has almost enough,
but there's one specific thing we need to change, we use overriding.

### Rules for overriding
- Name, parameters, and return type must match the parent method.
- Java picks which method to run at run time, based on the actual object type, not just the reference variable type.
- Static methods cannot be overridden.
- The @Override annotation catches mistakes like typos in method names.

## What is Overloading
Overloading refers to a number of functions that have the same name but have different use cases. For example, we could
have multiple constructors for a `User` class. One constructor could be a standard user that takes and email and
password, but another user constructor could accommodate users with no email or password (guest users).

For Java to identify overloaded methods, methods with the same name must have a unique signature, meaning their
parameters must be named or ordered differently, or the method could have a different return type.

