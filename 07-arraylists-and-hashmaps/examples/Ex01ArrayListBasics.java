// Example 1: an ArrayList is like an array that can GROW and SHRINK.
// Run it with:  java Ex01ArrayListBasics.java

import java.util.ArrayList;

public class Ex01ArrayListBasics {
    public static void main(String[] args) {
        ArrayList<String> queue = new ArrayList<>();     // an empty list of Strings
        System.out.println("Empty: " + queue + ", size " + queue.size());

        queue.add("Ama");            // add to the end
        queue.add("Kojo");
        queue.add("Esi");
        System.out.println("After adding: " + queue);    // lists print nicely!

        queue.add(0, "Yaw");         // insert at index 0; everyone else moves along
        System.out.println("Yaw pushed in: " + queue);

        System.out.println("First: " + queue.get(0));                    // like queue[0] for an array
        System.out.println("Last:  " + queue.get(queue.size() - 1));
        System.out.println("Size:  " + queue.size());                    // like length for an array

        queue.set(1, "AMA");         // replace the value at index 1
        System.out.println("After set: " + queue);

        System.out.println("Contains Esi? " + queue.contains("Esi"));
        System.out.println("Where is Kojo? " + queue.indexOf("Kojo"));
        System.out.println("Where is Efua? " + queue.indexOf("Efua"));   // -1: not there

        queue.remove("Kojo");        // remove by value
        queue.remove(0);             // remove by index
        System.out.println("After removing: " + queue);

        queue.clear();
        System.out.println("After clear: " + queue + ", isEmpty: " + queue.isEmpty());
    }
}
