// Example 2: lists can't hold primitives (int, double...) directly. Use the
// "wrapper" classes instead: Integer, Double, Boolean, Character.
// Run it with:  java Ex02WrapperClasses.java

import java.util.ArrayList;

public class Ex02WrapperClasses {
    public static void main(String[] args) {
        // ArrayList<int> doesn't compile. ArrayList<Integer> does.
        ArrayList<Integer> marks = new ArrayList<>();
        marks.add(72);               // Java "boxes" the int 72 into an Integer for you
        marks.add(85);
        marks.add(64);
        int first = marks.get(0);    // ...and "unboxes" it again
        System.out.println(marks + ", first = " + first);

        int total = 0;
        for (int m : marks) {        // unboxing happens in the loop too
            total += m;
        }
        System.out.println("Total: " + total);

        // TRAP 1: remove(int) removes by INDEX. To remove a VALUE, box it yourself.
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(1);
        numbers.remove(1);                       // removes index 1 (the 20)!
        System.out.println(numbers);             // [10, 1]
        numbers.remove(Integer.valueOf(1));      // removes the value 1
        System.out.println(numbers);             // [10]

        // TRAP 2: compare Integers with .equals(), not ==
        Integer a = 1000, b = 1000;
        System.out.println(a == b);              // false: two different objects
        System.out.println(a.equals(b));         // true

        ArrayList<Double> prices = new ArrayList<>();
        prices.add(4.5);
        prices.add(12.0);
        System.out.println(prices);
    }
}
