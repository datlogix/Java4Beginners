// Example 4: polymorphism. A variable of the SUPERCLASS type can hold any
// subclass object, and calling a method runs the OBJECT's own version.
// Run it with:  java Ex04Polymorphism.java

import java.util.ArrayList;
import java.util.List;

public class Ex04Polymorphism {

    static class Animal {
        protected final String name;
        Animal(String name) { this.name = name; }
        String sound() { return "..."; }
        String speak() { return name + " says " + sound(); }
    }

    static class Dog extends Animal {
        Dog(String name) { super(name); }
        @Override String sound() { return "Woof!"; }
    }

    static class Cat extends Animal {
        Cat(String name) { super(name); }
        @Override String sound() { return "Meow."; }
    }

    static class Goat extends Animal {
        Goat(String name) { super(name); }
        @Override String sound() { return "Meeeh!"; }
    }

    // This method works with EVERY kind of Animal, including kinds that
    // haven't been invented yet.
    static void chorus(List<Animal> animals) {
        for (Animal a : animals) {
            System.out.println(a.speak());
        }
    }

    public static void main(String[] args) {
        Animal pet = new Dog("Rex");         // an Animal variable holding a Dog
        System.out.println(pet.speak());     // Rex says Woof! (the Dog's sound)

        pet = new Cat("Tom");                // the same variable, now a Cat
        System.out.println(pet.speak());     // Tom says Meow.

        List<Animal> farm = new ArrayList<>();
        farm.add(new Dog("Bingo"));
        farm.add(new Goat("Akosua"));
        farm.add(new Cat("Felix"));
        farm.add(new Goat("Kwabena"));
        chorus(farm);

        // pet.fetch();   // even if pet holds a Dog, an Animal variable only allows Animal methods
    }
}
