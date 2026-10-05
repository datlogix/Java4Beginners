// Example 7: comparison operators give a boolean: true or false.
// Run it with:  java Ex07Comparisons.java

public class Ex07Comparisons {
    public static void main(String[] args) {
        System.out.println(5 > 3);         // true
        System.out.println(5 < 3);         // false
        System.out.println(5 >= 5);        // true
        System.out.println(5 <= 4);        // false
        System.out.println(5 == 5);        // true:  == compares (two equals signs)
        System.out.println(5 != 5);        // false: != means "not equal"

        // Combine booleans with && (and), || (or), ! (not). Module 4 uses these a lot.
        int age = 17;
        System.out.println(age >= 13 && age <= 19);   // true: a teenager
        System.out.println(!(age >= 18));             // true: not yet 18

        // Strings are compared with .equals(), NOT == (Module 4 explains why).
        System.out.println("java".equals("java"));    // true
        System.out.println("java".equals("Java"));    // false: capitals matter
    }
}
