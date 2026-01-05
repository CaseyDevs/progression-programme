# Stack Cont'd...

## Methods on the stack
Every time a method is called, the JVM creates a stack frame for that method. When the method returns the stack frame
is destroyed. 

### What is a stack frame?
A stack frame is a small container of stack memory that is stored on a thread's call stack. They are created when a
method is invoked, the methods local variables, parameters, an operand stack, return address, etc...

### What does the stack frame contain?
A stack frame typically contains:
- Local variables
- Operand stack (used by bytecode instructions)
- Return address (where execution continues after return)
- Some method metadata (constant pool references, etc...)

### What happens when the method returns?
- The methods stack frame is popped off the call stack
- Local variables are discarded
- Return value is passed back to the caller
- Execution of the next instruction (if any) resumes

