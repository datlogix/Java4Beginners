// Example 3: looping over a list, and removing items safely.
// Run it with:  java Ex03LoopingAndRemoving.java

import java.util.ArrayList;
import java.util.Iterator;

public class Ex03LoopingAndRemoving {
    public static void main(String[] args) {
        ArrayList<String> tasks = new ArrayList<>();
        tasks.add("buy solder");
        tasks.add("test motor");
        tasks.add("buy wire");
        tasks.add("write report");
        tasks.add("buy LEDs");

        // for-each, just like arrays
        for (String task : tasks) {
            System.out.println("- " + task);
        }

        // Index loop, when you need the position
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }

        // WRONG: removing inside a for-each loop crashes. Uncomment to see it:
        // for (String task : tasks) {
        //     if (task.startsWith("buy")) {
        //         tasks.remove(task);      // ConcurrentModificationException
        //     }
        // }

        // RIGHT, way 1: loop BACKWARDS by index, so removing doesn't shift what's left to visit
        ArrayList<String> copy = new ArrayList<>(tasks);
        for (int i = copy.size() - 1; i >= 0; i--) {
            if (copy.get(i).startsWith("buy")) {
                copy.remove(i);
            }
        }
        System.out.println("Way 1: " + copy);

        // RIGHT, way 2: an Iterator, which knows how to remove safely
        Iterator<String> it = tasks.iterator();
        while (it.hasNext()) {
            if (it.next().startsWith("buy")) {
                it.remove();
            }
        }
        System.out.println("Way 2: " + tasks);
    }
}
