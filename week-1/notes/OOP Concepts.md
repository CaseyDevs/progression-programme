# Definitions

1. Instance Values (state): Things an object knows about itself e.g. A song should know its title and artist. They
represent an objects state (the data), and can have unique values for each object of that type.
2. Methods: Things an object can do (functions).

# Difference between Classes and Objects

A class is not an object, but it is used to construct them.

Classes act as a blueprint for an object, telling the virtual machine how an object of that particular type needs to be
made. Each object within the class can hold its own values for instance variables of that class. For example, we could 
have a BankAccount class, where different instances of BankAccounts (acc1, acc2, ...) have different balances, acc nums, 
etc.

## BULLET POINTS
- Object-oriented programming lets you extend a program without having to touch previously-tested, working code.
- All Java code is defined in a class.
- A class describes how to make an object of that class type. 
- A class is like a blueprint.
- An object can take care of itself; you don’t have to know or care how the object does it.
- An object knows things and does things.
- Things an object knows about itself are called instance variables. They represent the state of an object.
- Things an object does are called methods.
- They represent the behavior of an object.
- When you create a class, you may also want to create a separate test class which you’ll use to create objects of your 
new class type.
-  class can inherit instance variables and methods from a more abstract superclass.
- At runtime, a Java program is nothing more than objects ‘talking’ to other objects.