// Example 4: the algorithms you'll write again and again with arrays.
// Run it with:  java Ex04ArrayAlgorithms.java

public class Ex04ArrayAlgorithms {
    public static void main(String[] args) {
        double[] temperatures = {31.5, 29.0, 33.2, 35.1, 30.4, 28.7, 32.0};
        String[] days = {"Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"};

        // Total and average
        double total = 0;
        for (double t : temperatures) {
            total += t;
        }
        System.out.printf("Average: %.1f C%n", total / temperatures.length);

        // Maximum AND where it is: remember the index, not just the value
        int hottest = 0;
        for (int i = 1; i < temperatures.length; i++) {
            if (temperatures[i] > temperatures[hottest]) {
                hottest = i;
            }
        }
        System.out.println("Hottest: " + days[hottest] + " (" + temperatures[hottest] + " C)");

        // Count the values that match a condition
        int above30 = 0;
        for (double t : temperatures) {
            if (t > 30) {
                above30++;
            }
        }
        System.out.println("Days above 30 C: " + above30);

        // Linear search: is a value there, and where?
        String wanted = "Fri";
        int position = -1;                      // -1 means "not found yet"
        for (int i = 0; i < days.length; i++) {
            if (days[i].equals(wanted)) {
                position = i;
                break;
            }
        }
        System.out.println(wanted + " is at index " + position);
    }
}
