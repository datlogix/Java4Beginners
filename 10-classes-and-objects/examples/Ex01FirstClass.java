// Example 1: a class is a blueprint; objects are things built from it.
// Run it with:  java Ex01FirstClass.java

public class Ex01FirstClass {

    // A class with three FIELDS: the data every Dog object has.
    // (The examples keep each class inside the file, marked static, so that
    //  "java Ex01FirstClass.java" can run them. In your projects, each class
    //  goes in its own file: see the README.)
    static class Dog {
        String name;
        String breed;
        int age;
    }

    public static void main(String[] args) {
        Dog rex = new Dog();          // new creates an object (an "instance") of the class
        rex.name = "Rex";             // the dot reaches into THIS object's fields
        rex.breed = "Labrador";
        rex.age = 3;

        Dog bingo = new Dog();        // a second, completely separate object
        bingo.name = "Bingo";
        bingo.breed = "Basenji";
        bingo.age = 5;

        System.out.println(rex.name + " is a " + rex.breed + ", aged " + rex.age);
        System.out.println(bingo.name + " is a " + bingo.breed + ", aged " + bingo.age);

        bingo.age++;                  // changing bingo doesn't touch rex
        System.out.println("Rex: " + rex.age + ", Bingo: " + bingo.age);

        Dog nobody = new Dog();       // fields start with default values
        System.out.println(nobody.name + " " + nobody.age);   // null 0
    }
}
