// Example 5: Java's ready-made functional interfaces, in java.util.function.
// Run it with:  java Ex05FunctionalInterfaces.java

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

public class Ex05FunctionalInterfaces {
    public static void main(String[] args) {
        Predicate<Integer> isEven = n -> n % 2 == 0;            // T -> boolean   (a test)
        Function<String, Integer> length = s -> s.length();     // T -> R         (a conversion)
        UnaryOperator<String> shout = s -> s.toUpperCase();     // T -> T
        BiFunction<Double, Double, Double> ohms = (v, i) -> v / i;
        Consumer<String> printer = s -> System.out.println(">> " + s);   // T -> nothing (an action)
        Supplier<Double> dice = () -> Math.floor(Math.random() * 6) + 1; // nothing -> T (a source)

        System.out.println(isEven.test(10) + " " + isEven.test(7));
        System.out.println(length.apply("Ghana"));
        System.out.println(shout.apply("hello"));
        System.out.println(ohms.apply(12.0, 0.5) + " ohms");
        printer.accept("a message");
        System.out.println("Rolled " + dice.get());

        // They can be combined:
        Predicate<Integer> isBig = n -> n > 100;
        Predicate<Integer> bigAndEven = isBig.and(isEven);
        System.out.println(bigAndEven.test(120) + " " + bigAndEven.test(121) + " " + isEven.negate().test(3));
        Function<String, Integer> shoutedLength = shout.andThen(length);
        System.out.println(shoutedLength.apply("abc"));

        // Methods can TAKE functions as arguments: that's what makes them so powerful.
        System.out.println(countMatching(List.of(4, 7, 10, 13, 16), isEven));
        System.out.println(countMatching(List.of("ox", "elephant", "cat"), s -> s.length() > 2));
    }

    static <T> int countMatching(List<T> items, Predicate<T> test) {
        int count = 0;
        for (T item : items) {
            if (test.test(item)) {
                count++;
            }
        }
        return count;
    }
}
