// Example 2: a constructor sets up a new object's fields when it's created.
// Run it with:  java Ex02Constructors.java

public class Ex02Constructors {

    static class Dog {
        String name;
        String breed;
        int age;

        // A constructor has the class's name and NO return type.
        Dog(String name, String breed, int age) {
            this.name = name;      // this.name is the field; name is the parameter
            this.breed = breed;
            this.age = age;
        }

        // Overloaded constructor: for when we don't know the breed
        Dog(String name, int age) {
            this(name, "mixed breed", age);    // call the other constructor
        }
    }

    public static void main(String[] args) {
        Dog rex = new Dog("Rex", "Labrador", 3);
        Dog patch = new Dog("Patch", 1);
        System.out.println(rex.name + ", " + rex.breed + ", " + rex.age);
        System.out.println(patch.name + ", " + patch.breed + ", " + patch.age);

        // Once you write a constructor, the empty one is gone:
        // Dog nobody = new Dog();     // error: constructor Dog cannot be applied
    }
}
