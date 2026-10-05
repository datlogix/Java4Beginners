// Example 1: BROKEN ON PURPOSE. A crash three method calls deep.
// Run it and read the stack trace: the first line says WHAT went wrong,
// and the "at" lines say WHERE, most recent call first.
// Run it with:  java Ex01ReadingStackTraces.java

public class Ex01ReadingStackTraces {

    static int average(int[] marks) {
        int total = 0;
        for (int m : marks) {
            total += m;
        }
        return total / marks.length;            // line 13: crashes if the array is empty
    }

    static String report(String name, int[] marks) {
        return name + ": average " + average(marks);    // line 17
    }

    public static void main(String[] args) {
        System.out.println(report("Akua", new int[]{70, 80, 90}));
        System.out.println(report("Kwesi", new int[]{}));   // line 22
        System.out.println("This line never runs.");
    }
}
