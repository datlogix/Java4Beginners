// Example 1: why inheritance? Three classes that repeat the same code,
// and then the same three classes sharing it through a superclass.
// Run it with:  java Ex01WhyInheritance.java

public class Ex01WhyInheritance {

    // BEFORE: every class repeats name, age and describe(). Fix a bug in one,
    // and you have to remember to fix it in all three.
    static class DogBefore {
        String name; int age;
        DogBefore(String name, int age) { this.name = name; this.age = age; }
        String describe() { return name + ", aged " + age; }
        String sound() { return "Woof"; }
    }
    static class CatBefore {
        String name; int age;
        CatBefore(String name, int age) { this.name = name; this.age = age; }
        String describe() { return name + ", aged " + age; }
        String sound() { return "Meow"; }
    }

    // AFTER: the shared parts live ONCE, in Animal. Dog and Cat EXTEND it:
    // they get everything Animal has, and add (or change) only what's different.
    static class Animal {
        protected final String name;
        protected final int age;

        Animal(String name, int age) {
            this.name = name;
            this.age = age;
        }

        String describe() {
            return name + ", aged " + age;
        }
    }

    static class Dog extends Animal {
        Dog(String name, int age) {
            super(name, age);          // let Animal's constructor set up name and age
        }
        String sound() { return "Woof"; }
        void fetch() { System.out.println(name + " fetches the stick!"); }
    }

    static class Cat extends Animal {
        Cat(String name, int age) {
            super(name, age);
        }
        String sound() { return "Meow"; }
    }

    public static void main(String[] args) {
        Dog rex = new Dog("Rex", 3);
        Cat tom = new Cat("Tom", 5);
        System.out.println(rex.describe() + " says " + rex.sound());   // describe() came from Animal
        System.out.println(tom.describe() + " says " + tom.sound());
        rex.fetch();
        // tom.fetch();     // error: Cats don't have fetch(). Only Dog added it.
    }
}
