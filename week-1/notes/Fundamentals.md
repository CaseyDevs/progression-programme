# The Compiler & JVM
The compiler takes the interpreted Java source code and compiles it into bytecode. The process of converting
the source code enables the Java Virtual Machine (JVM) to significantly improve the execution time of a Java
program. It checks the entire program for syntax violations, valid variable values based on their typing,
prevents access violations.

The JVM works at runtime, making Java code actually run. It is responsible for throwing exceptions such as
ClassCastExceptions for example. It also recognises when incorrect types are stored in an array, and acts as
a second line of defense.

# What is JDK ?
JDK (Java Development Kit) is a tool used to run Java Code. The JDK includes the Java interpreter,
Java classes, and Java development tools--compiler, debugger, dissssembler, appletviewer, stub file generator,
and documentation generator. 

JDK = allows you to develop Java (compiler, debugger)

JRE = allows you to run Java

Esentially, it is software that allows us to compile, debug, and run Java programs.

# Variables

There are 2 types of variables in Java. Primitive and Reference variables.

Primitive variables are simple values that are stored directly in memory on the stack.
They store actual values.

### Primitive Types:
1. int
2. char
3. bool
4. double
5. float
6. long double

Reference variables are more complex values which store memory addresses on the stack that point to the heap.
They store a pointer to an object on the heap.
### Reference Types:
1. Array
2. Vector
3. Object
4. String
5. Class
6. Interface

# Pass-by Value
Java is a pass-by-value-only language. This means that when say a variable is passed as an argument to a method, a copy
of the variable (its bits) are sent to the method, rather than a reference to the variable. This essentially creates
another variable in memory.

Remember - pass-by-value is pass-by copy.

# The Stack and Heap

## Stack
Stack memory is used for static memory allocation and the execution of a thread. It contains primitive values that are specific to a method and references to objects referred from the method that are in a heap.

Access to stacks is LIFO (Last in first out), so the last values to be added to the stack will be the first values to exit. When a new method is called, a new block is created on the top of the stack, containing values specific that method--primitive variables, references to objects.

When the method finishes execution, its corresponding stack frame is flushed, the flow goes back to the calling method, and space becomes available for the next method.

### Key Features of Stack
- Grows and shrinks as methods are called and returned respectively.
- Variables in the stack only exist while their corresponding method is running.
- Automatically allocated and deallocated when the method finishes execution.
- If this memory is full, Java throws java.lang.StackOverFlowError.
- Access to this memory is much faster than accessing heap memory.
- Threadsafe, as each thread runs on its own stack--the fields of an object or class always maintain a valid state, as observed by other objects and classes, even when used concurrently by multiple threads. 

## Heap
Heap space is used for dynamic memory allocation of Java Objects and JRE classes at runtime. New objects are always created in heap space, and the references to these objects are stored in stack memory.

Due to the nature of heap memory, these objects can be access globally--from anywhere in the application.

We can break this memory model down into smaller parts, called generations, which are:

Young Generation – this is where all new objects are allocated and aged. A minor Garbage collection occurs when this fills up.

Old or Tenured Generation – this is where long surviving objects are stored. When objects are stored in the Young Generation, a threshold for the object’s age is set, and when that threshold is reached, the object is moved to the old generation.

Permanent Generation – this consists of JVM metadata for the runtime classes and application methods.

### Key Features of Heap
- Accessed via complex memory management techniques-- Young generation, Old or tenured generation, Permanent generation.
- If heap space is full, Java throws java.lang.OutOfMemoryError.
- Access to heap memory is comparitively slower than stack memory.
- This memory, in contrast to stack, isn’t automatically deallocated. It needs Garbage Collector to free up unused objects so as to keep the efficiency of the memory usage.
- Unlike stack, a heap isn’t threadsafe and needs to be guarded by properly synchronizing the code.

Stack → stores primitive values + references + method calls.
Fast, automatic, short-lived.

Heap → stores actual objects created with new.
Slower, shared, long-lived.

![](https://miro.medium.com/v2/resize:fit:1400/format:webp/1*TRrCTXjuOzGE17rKqLXP4Q.png)

## Static Keyword
The static keyword is a non-access modifier used for methods and attributes, enabling them to be accessed without the
initialisation of an object of a class. If you have a Car class and an OpenDoor() method then you have to have an 
instance of the Car class in order to be able to call OpenDoor() on it.

On the other hand, if there is a method called MilesPerGallonToLitresPer100Km() you don't have to have a Car object 
for it to make sense - it can be static, operating within the class, but not on any concrete object belonging to that 
class.

- Use static for variables that are shared by all objects of the class.
- Don't use static for values that are unique for each object (instance variables).
