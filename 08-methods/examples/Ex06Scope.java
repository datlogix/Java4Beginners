// Example 6: scope. A variable only exists inside the braces where it was created.
// Run it with:  java Ex06Scope.java

public class Ex06Scope {

    // A constant declared in the class (outside every method) is visible everywhere in it.
    static final double VAT_RATE = 0.15;

    static double withVat(double price) {
        double vat = price * VAT_RATE;     // vat is LOCAL to withVat
        return price + vat;
    }

    static void tryToChange(int number) {
        number = 999;                      // changes the parameter: a local COPY
        System.out.println("  inside tryToChange, number = " + number);
    }

    public static void main(String[] args) {
        System.out.println(withVat(100));
        // System.out.println(vat);        // error: cannot find symbol. vat only lives in withVat

        int number = 5;
        tryToChange(number);
        System.out.println("back in main, number = " + number);   // still 5!

        // Two methods can each have their own variable with the same name.
        // They're completely separate boxes.
        int price = 10;
        System.out.println(withVat(price));
        System.out.println("price in main is still " + price);
    }
}
