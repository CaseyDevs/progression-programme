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