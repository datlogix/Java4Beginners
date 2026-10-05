// Example 4: finally runs whether or not an exception happened. It's where
// clean-up goes: closing files, releasing equipment, printing a footer.
// Run it with:  java Ex04Finally.java

public class Ex04Finally {

    static void useEquipment(String user, boolean breakIt) {
        System.out.println(user + " switches the oscilloscope on.");
        try {
            if (breakIt) {
                throw new IllegalStateException(user + " pressed the wrong button!");
            }
            System.out.println(user + " takes a measurement.");
        } catch (IllegalStateException e) {
            System.out.println("Problem: " + e.getMessage());
        } finally {
            System.out.println(user + " switches it off again.");   // ALWAYS runs
        }
        System.out.println();
    }

    static int tricky() {
        try {
            return 1;
        } finally {
            System.out.println("finally runs even after return");
        }
    }

    public static void main(String[] args) {
        useEquipment("Ama", false);
        useEquipment("Kojo", true);
        System.out.println("tricky() returned " + tricky());
    }
}
