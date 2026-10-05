// Example 3: parameters let you hand values to a method.
// Run it with:  java Ex03Parameters.java

public class Ex03Parameters {

    // One parameter: a String called name
    static void greet(String name) {
        System.out.println("Akwaaba, " + name + "!");
    }

    // Several parameters, of different types, separated by commas
    static void printReceiptLine(String item, int quantity, double price) {
        System.out.printf("%-12s x%-3d GHS %7.2f%n", item, quantity, quantity * price);
    }

    static void drawLine(char symbol, int length) {
        for (int i = 0; i < length; i++) {
            System.out.print(symbol);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        greet("Afua");                       // "Afua" is the ARGUMENT; name is the PARAMETER
        greet("Kwabena");
        String friend = "Yaa";
        greet(friend);                       // an argument can be a variable...
        greet(friend.toUpperCase() + "!");   // ...or any expression of the right type

        drawLine('=', 30);
        printReceiptLine("Breadboard", 2, 25.0);
        printReceiptLine("Jumper wire", 40, 0.5);
        drawLine('-', 30);

        // The arguments must match the parameters in number, order and type:
        // printReceiptLine(2, "Breadboard", 25.0);   // error: incompatible types
        // greet();                                    // error: actual and formal argument lists differ
    }
}
