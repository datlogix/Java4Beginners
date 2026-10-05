// Example 5: lambdas. An interface with exactly ONE abstract method is a
// "functional interface", and you can implement it with a tiny inline
// function instead of a whole class.
// Run it with:  java Ex05Lambdas.java

public class Ex05Lambdas {

    interface Operation {
        double apply(double a, double b);    // ONE abstract method
    }

    // The long way: a named class implementing the interface
    static class Add implements Operation {
        public double apply(double a, double b) {
            return a + b;
        }
    }

    static void show(String name, Operation op) {
        System.out.println(name + "(12, 4) = " + op.apply(12, 4));
    }

    public static void main(String[] args) {
        show("add", new Add());

        // The short way: a lambda.  (parameters) -> result
        show("subtract", (a, b) -> a - b);
        show("multiply", (a, b) -> a * b);
        show("power", (a, b) -> Math.pow(a, b));

        // A lambda with several statements needs braces and a return
        show("safeDivide", (a, b) -> {
            if (b == 0) {
                return 0;
            }
            return a / b;
        });

        // A lambda can be stored in a variable, like any object
        Operation hypotenuse = (a, b) -> Math.sqrt(a * a + b * b);
        System.out.println("hypotenuse(3, 4) = " + hypotenuse.apply(3, 4));

        // Runnable is a built-in functional interface: no parameters, no result
        Runnable greet = () -> System.out.println("Hello from a lambda!");
        greet.run();
    }
}
