
* Objects of a superclass should be replaceable with objects of its subclasses without breaking the application.

```java
import java.util.ArrayList;
import java.util.List;

// ========================= BAD EXAMPLE - VIOLATING LSP =========================

// Base class
class Bird {
    public void fly() {
        System.out.println("Bird is flying");
    }
    
    public void eat() {
        System.out.println("Bird is eating");
    }
}

// This violates LSP - Penguin can't fly but inherits fly() method
class Penguin extends Bird {
    @Override
    public void fly() {
        // This violates LSP - we're changing expected behavior
        throw new UnsupportedOperationException("Penguins cannot fly!");
    }
    
    public void swim() {
        System.out.println("Penguin is swimming");
    }
}

class Eagle extends Bird {
    @Override
    public void fly() {
        System.out.println("Eagle is soaring high");
    }
}

// This class expects all birds to be able to fly - breaks when Penguin is used
class BirdController {
    public void makeBirdFly(Bird bird) {
        bird.fly(); // This will throw exception for Penguin!
    }
}

// ========================= GOOD EXAMPLE - FOLLOWING LSP =========================

// Better design using interfaces and proper inheritance
interface Animal {
    void eat();
    void makeSound();
}

interface Flyable {
    void fly();
}

interface Swimmable {
    void swim();
}

// Base bird class implementing common behavior
abstract class BirdBase implements Animal {
    protected String species;
    
    public BirdBase(String species) {
        this.species = species;
    }
    
    @Override
    public void eat() {
        System.out.println(species + " is eating");
    }
    
    public String getSpecies() {
        return species;
    }
}

// Flying birds implement Flyable interface
class EagleGood extends BirdBase implements Flyable {
    public EagleGood() {
        super("Eagle");
    }
    
    @Override
    public void fly() {
        System.out.println("Eagle is soaring high in the sky");
    }
    
    @Override
    public void makeSound() {
        System.out.println("Eagle makes a screeching sound");
    }
}

class Sparrow extends BirdBase implements Flyable {
    public Sparrow() {
        super("Sparrow");
    }
    
    @Override
    public void fly() {
        System.out.println("Sparrow is flying quickly");
    }
    
    @Override
    public void makeSound() {
        System.out.println("Sparrow chirps");
    }
}

// Non-flying birds don't implement Flyable
class PenguinGood extends BirdBase implements Swimmable {
    public PenguinGood() {
        super("Penguin");
    }
    
    @Override
    public void swim() {
        System.out.println("Penguin is swimming gracefully");
    }
    
    @Override
    public void makeSound() {
        System.out.println("Penguin makes a trumpeting sound");
    }
}

// Birds that can both fly and swim
class Duck extends BirdBase implements Flyable, Swimmable {
    public Duck() {
        super("Duck");
    }
    
    @Override
    public void fly() {
        System.out.println("Duck is flying over the water");
    }
    
    @Override
    public void swim() {
        System.out.println("Duck is swimming on the pond");
    }
    
    @Override
    public void makeSound() {
        System.out.println("Duck quacks");
    }
}
```

---

```java

// ========================= BAD EXAMPLE - VIOLATING LSP =========================

public class Rectangle {
    private double height;
    private double width;
    public void setHeight(double h) { height = h; }
    public void setWidht(double w) { width = w; }
    ...
}

public class Square extends Rectangle {
    public void setHeight(double h) {
        super.setHeight(h);
        super.setWidth(h);
    }
    public void setWidth(double w) {
        super.setHeight(w);
        super.setWidth(w);
    }
}

// ========================= GOOD EXAMPLE - FOLLOWING LSP =========================

abstract class Shape {
    public abstract double getArea();
}

class Rectangle extends Shape {
    private double height;
    private double width;

    public Rectangle(double height, double width) {
        this.height = height;
        this.width = width;
    }

    @Override
    public double getArea() {
        return height * width;
    }
}

class Square extends Shape {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    @Override
    public double getArea() {
        return side * side;
    }
}

public class Main {
    public static void main(String[] args) {
        Shape rectangle = new Rectangle(7.0, 5.0);
        Shape square = new Square(3.0);

        System.out.println("Rectangle Area: " + rectangle.getArea());
        System.out.println("Square Area: " + square.getArea());
    }
}
```
