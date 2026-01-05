# Primitive Variables

All primitive values have a fixed number of bits.

### Bit Depth Table

| Type       | Bit Depth      | Value Range               |
|------------|----------------|---------------------------|
| boolean    | (JVM Specific) | true false                |  
| char       | 8              | 0 to 65535                | 
| byte       | 8              | -128 to 127               |
| short      | 16             | -32768 to 32767           |
| int        | 32             | -2147483648 to 2147483647 |
| long       | 64             | -huge to huge             |
| float      | 32             | varies                    |
| double     | 64             | varies                    |  

The compiler will not allow us to store variables with larger value ranges into smaller ones, despite not knowing
what the value of the smaller variable is. For example, if we have int x = 24, and byte b = x, the compiler will throw
an error. Despite 24 being able to fit in a byte based on its range, the compiler is built to ignore this and show more
concern of trying to fit essentially a large container into a smaller one. 

# Reference Variables

There's no such thing as an object variable. There's only an object reference variable, it stores a pointer or
memory address. In Java, we don't know what's inside the reference variable, but we do know that whatever it is, 
represents one and only one object. The JVM knows how to use the reference to get to the object.

Objects live in one place and one place only, the garbage collection heap. They cannot be stored inside of variables,
be passed into methods, or returned from methods as it may seem. Unlike primitive variables, object references are made
up of lots of bits that represent a way of getting to the object.

## Reference vs Obeject Reference
There's is a distinct difference between references and object references. Example:

**BankAccount a = new BankAccount();**

**BankAccount b = a;**

In this example, variable 'a' holds a reference(pointer) to the actual object in the heap. Variable 'b' holds that
same reference by pointing at variable 'a', rather than creating a new instance of BankAccount, therefore pointing
at a different object in heap space.

## Pass-by values (Java's real behaviour)
Java **ALWAYS** passes by value. The value of a reference is a pointer. So, for primitives, Java gives us a copy of
the value, meaning changes stay local. For references, a copy of the reference is made which still points to the same
object in memory, but changes reflect on the outside.

```
void update(BankAccount acc) {
    acc.deposit(100); // this WILL modify original
}
```

````
void update(BankAccount acc) {
    acc = new BankAccount(999); // this will NOT modify original
}
````

## Garbage Collection and Expansion Heap
'Garbage collection' and 'heap expansion' are an essential part in the operation of JVM. Their trigger frequency is dependent
on the amount of garbage (objects), created by applications running in the JVM.

When a JVM is unable to allocate a new object in the heap space (allocation failure), a garbage collection is triggered.
When triggered, the garbage collector removes redundant objects that are no longer being referenced to within
the application.

If the garbage collector is unable to clear any objects, it expands the storage heap. During heap expansion, 
the Garbage Collector takes storage from the maximum amount of storage reserved for the heap (the amount specified by 
the -Xmx option), and adds it to the active part of the heap (which began as the size specified by the -Xms option). 
Heap expansion does not increase the amount of storage required for the JVM, because the maximum amount of storage 
specified by the -Xmx option has already been allocated to the JVM at startup. If the value of the -Xms option provides 
sufficient storage in the active part of the heap for your applications, the Garbage Collector does not have to carry 
out heap expansion at all.

At some point during the lifetime of the JVM, the Garbage Collector stops expanding the storage heap, because the heap 
has reached a state where the Garbage Collector is satisfied with the frequency of garbage collection and the amount of 
space freed by the process. The Garbage Collector does not aim to eliminate allocation failures, so some garbage 
collection can still be triggered by allocation failures after the Garbage Collector has stopped expanding the storage 
heap.

# Bulletpoints
- 2 types of variables: Primitive & Reference
- Variables must be declared with a name and type
- A primitive variable value is the bits representing the values (5,'a',true,1.5 etc)
- A reference value is the bits representing how to get to the object on the heap (pointer)
- A reference variable is like a remote control. Using the dot operator (.) on a reference variable is like pressing 
a button on the remote control to access a method or instance variable.
- A reference has a value of null if it is not referencing / pointing to any object.
- An array is always an object, even if the array
  is declared to hold primitives. There is no such
  thing as a primitive array, only an array that
  holds primitives.