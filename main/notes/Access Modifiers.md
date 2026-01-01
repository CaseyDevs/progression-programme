# Definition
Access modifiers in Java are keywords that are used to set the visibility (access level) of classes methods and
variables. They determine which other parts of the program--classes, packages, etc-- can access a particular member.
This helps enforce encapsulation and protects the internal states of objects.

## The 4 Main Modifiers in Java
***1. public:*** Members are accessible from any other class

***2. protected:*** The member is accessible within its own package and by subclasses, even when they are in different
packages.

***3. default:*** If an access modifier is not specified, Java uses a package-private (default) access.

***4. private:*** The member is accessible only within the same class.

### Summary Table
| Modifier   | Same Class | Same Package | Subclass (other package) | Everywhere |
|------------|:----------:|:------------:|:-----------------------:|:----------:|
| public     |     ✔      |      ✔       |           ✔             |     ✔      |
| protected  |     ✔      |      ✔       |           ✔             |     ✖      |
| (default)  |     ✔      |      ✔       |           ✖             |     ✖      |
| private    |     ✔      |      ✖       |           ✖             |     ✖      |