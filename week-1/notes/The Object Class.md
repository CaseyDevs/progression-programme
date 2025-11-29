# What is the Object class ?
The object class lives at the top on the class hierarchy in Java applications. Every superclass in Java is simply
an extension of the Object class, so effectively every super class has a super class. Without the object class, it would
not be possible as a developer to write our own classes.

## Object class methods
The object class comes with numerous standard methods out of the box, but there are a few very important ones that are 
used most often.

1. `someObject.equals(someObjectTwo)` - Compares two objects tells us if they are considered "equal"
2. `someObject.getClass(someObject)` - Get the class that object was initiated from
3. `someObject.hashCode()` - Returns the hashcode of an object. This could be considered an ID.
4. `someObject.toString()` - Prints out a String message with the name of the class and some other member we rarely
care about.

Every class in Java inherits the Object class as the super class passed down the Object class methods to it own 
subclasses, so every object has access to the Object class methods.

## The issue with Object
The problem with having everything treated polymorphically as an Object is that the objects appear to lose 
(but not permanently) their true essence. The Dog appears to lose its dogness.

## How it works on the heap
Even if we have instantiated one object Dog (which in turn lives on the heap), that object will contain both its Dog
class parts of itself (vars, methods) aswell as the Object class part of itself.

Additionally, if we instantiate a Dog object 'd' and and Object object 'o' where 'd' points to a dog object and 'o'
points to 'd', 'o' will not be able to see the methods in 'd', it will only be able to see the Object methods like
toString() and getHashcode() etc... This can be visualised as if the remote control 'd' (reference) to dog has more 
buttons than the remote control 'o' to dog.