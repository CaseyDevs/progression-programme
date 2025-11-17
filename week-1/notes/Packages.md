# Definition
Packages in Java are a way to structure and orgranise code and group related classes,
interfaces. and sub-packages together. They act as folders/directories for Java files.

In simple terms - A package in Java is a way to group related classes and interfaces, making code more organized, 
manageable, and less prone to name conflicts

## Key Points
***Organisation:*** Help organise code into namespaces, making it easier to manage large projects
and avoid naming conflicts between classes.


***Access Control:*** Classes in one package can be restricted access to classes in another package,
allowing us to encapsulate implementation details.

***Reusability*** Code grouped in packages can be reused across different projects by importing the 
package.

## Example
Several classes may be implemented related to banking. These packages may be grouped under a package named
`com.example.bank`:

```java
package com.example.bank;

public class BankAccount {
    // Bank account code
}
```

To import a class from a packge, we can use an import statement at the top of the Java file.

`import com.example.bank.BankAccount`


## Built-in Packages
Java provides numerous built-in packages ready for use (like `java.util`, `java.io`, `java.lang`) that offer
commonly used classes to speed up the development process.

`import java.util.Scanner`

