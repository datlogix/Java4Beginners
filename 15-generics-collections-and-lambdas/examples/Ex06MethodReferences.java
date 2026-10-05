// Example 6: method references. When a lambda just calls one existing method,
// you can name the method instead.
// Run it with:  java Ex06MethodReferences.java

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class Ex06MethodReferences {

    static boolean isVowel(char c) {
        return "aeiouAEIOU".indexOf(c) >= 0;
    }

    public static void main(String[] args) {
        List<String> names = new ArrayList<>(List.of("kofi", "ama", "yaw"));

        // 1. A static method:            Class::staticMethod
        Function<String, Integer> parse = Integer::parseInt;      // same as s -> Integer.parseInt(s)
        System.out.println(parse.apply("41") + 1);

        // 2. A method of the object passed in:   Class::instanceMethod
        Function<String, String> upper = String::toUpperCase;     // same as s -> s.toUpperCase()
        System.out.println(upper.apply("akwaaba"));

        // 3. A method of one particular object:  object::method
        names.forEach(System.out::println);                       // same as n -> System.out.println(n)

        // 4. A constructor:              Class::new
        Supplier<List<String>> listMaker = ArrayList::new;        // same as () -> new ArrayList<>()
        List<String> fresh = listMaker.get();
        fresh.add("new list!");
        System.out.println(fresh);

        System.out.println(isVowel('e') + " " + isVowel('k'));
        names.sort(String::compareTo);
        System.out.println(names);
    }
}
