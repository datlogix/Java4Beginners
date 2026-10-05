// Example 5: exceptions are objects, arranged in a family tree.
// catch (Exception e) catches every kind of Exception below it.
// Run it with:  java Ex05Hierarchy.java

import java.util.ArrayList;
import java.util.List;

public class Ex05Hierarchy {

    public static void main(String[] args) {
        List<Runnable> accidents = new ArrayList<>();
        accidents.add(() -> Integer.parseInt("twelve"));
        accidents.add(() -> System.out.println(10 / 0));
        accidents.add(() -> {
            int[] boxes = new int[3];
            boxes[5] = 1;
        });
        accidents.add(() -> System.out.println("abc".charAt(10)));
        accidents.add(() -> {
            String s = null;
            s.length();
        });
        accidents.add(() -> List.of(1, 2).add(3));

        for (Runnable accident : accidents) {
            try {
                accident.run();
            } catch (Exception e) {                  // the parent of them all (almost)
                System.out.printf("%-35s is a RuntimeException? %s%n",
                        e.getClass().getSimpleName(), e instanceof RuntimeException);
            }
        }

        // Walk up one exception's family tree
        Class<?> c = NumberFormatException.class;
        StringBuilder tree = new StringBuilder();
        while (c != null) {
            tree.append(c.getSimpleName());
            c = c.getSuperclass();
            if (c != null) {
                tree.append(" -> ");
            }
        }
        System.out.println(tree);
    }
}
