// Example 1: a variable is a named box that holds one value of one type.
// Run it with:  java Ex01Variables.java

public class Ex01Variables {
    public static void main(String[] args) {
        // Declare AND give a first value (initialise) in one line:
        String name = "Kofi";
        int age = 19;
        double heightInMetres = 1.78;

        // Use the variables: Java looks up what's in each box.
        System.out.println(name + " is " + age + " years old and " + heightInMetres + " m tall.");

        // Declare first, assign later:
        int marks;
        marks = 74;
        System.out.println("Marks: " + marks);

        // Several variables of the same type at once:
        int width = 4, height = 3;
        System.out.println("Area: " + (width * height));

        // A variable can be used to calculate another:
        int ageInMonths = age * 12;
        System.out.println(name + " is about " + ageInMonths + " months old.");
    }
}
