// Example 6: formatting output with printf and String.format.
// Run it with:  java Ex06Printf.java

public class Ex06Printf {
    public static void main(String[] args) {
        String item = "Notebook";
        int quantity = 3;
        double price = 4.5;

        // %s = a String, %d = a whole number, %f = a decimal, %n = new line
        System.out.printf("%s x %d at %f each%n", item, quantity, price);

        // %.2f = exactly 2 decimal places (it rounds for you)
        System.out.printf("Total: GHS %.2f%n", quantity * price);
        System.out.printf("Pi to 3 places: %.3f%n", Math.PI);

        // A number before the letter sets a minimum WIDTH. - means left-align.
        System.out.printf("[%10s]%n", "right");    // [     right]
        System.out.printf("[%-10s]%n", "left");    // [left      ]
        System.out.printf("[%5d]%n", 42);          // [   42]
        System.out.printf("[%8.2f]%n", 3.14159);   // [    3.14]

        // %,d adds thousands separators
        System.out.printf("Population: %,d%n", 34_000_000);

        // A table lines up when every row uses the same widths:
        System.out.printf("%-12s %5s %8s%n", "Item", "Qty", "Price");
        System.out.printf("%-12s %5d %8.2f%n", "Pen", 10, 1.5);
        System.out.printf("%-12s %5d %8.2f%n", "Calculator", 1, 85.0);

        // String.format builds the same text but gives it back instead of printing it.
        String label = String.format("%s (%d)", item, quantity);
        System.out.println("Label: " + label);
    }
}
