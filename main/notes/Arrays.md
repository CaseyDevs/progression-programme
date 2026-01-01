# Definition 
Arrays are simple datastructures that enable fast, efficient, self-ordered data access. They are a list that can contain
numerous different things, such as numbers, strings, booleans, etc. They enable fast random access due to their
implementation of index values, so users can grab the position of any element in the array, assuming it exists.

Arrays are simply objects, so they are stored in the heap and accessed through references from that stack.

Each element in an array is just a variable, both primitive or reference. 

# Java Implementation
In Java, we define the type of elements in the array--providing a level of type safety--and also have the option to
define the size of the array. For example, to define an array of integers:

**int[] wholeNums = new int[5]** 

# ArrayList
ArrayList is part of the built-in `java.util` package. It comes pre-configured with number of methods such as getSize(),
add() etc.. 

ArrayLists act differently to standard arrays. Firstly, they are objects and considered dynamic. They can grow and
shrink, hold numerous types, and live on the Heap. Variables stored in ArrayLists are make into objects which the
ArrayList points to. Even primitive values are converted to objects in ArrayLists; they are boxed / wrapped into their
related object types. For example, and `int` value will be converted to a `Integer` object. It holds the same type, but
also comes with additional properties and methods.

## When to use which ?
We want to use standard arrays when we know the size of the array, especially in performance critical systems as
the conversion of values to objects has overhead, alongside the de-allocation of references in ArrayLists. 

Conversely, we want to choose ArrayLists when we do not know the size of the array, we want the array to have dynamic
behaviours, performance is not critical, and we want to store numerous types.