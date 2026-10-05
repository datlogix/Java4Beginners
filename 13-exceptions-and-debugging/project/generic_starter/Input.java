import java.util.Scanner;

/** Crash-proof keyboard input, shared by the whole program. (Your Exercise 1 helpers!) */
public class Input {
    private static final Scanner IN = new Scanner(System.in);

    public static String readLine(String prompt) {
        System.out.print(prompt);
        return IN.nextLine().trim();
    }

    public static int readInt(String prompt, int min, int max) {
        // TODO
        return min;
    }

    public static double readDouble(String prompt, double min, double max) {
        // TODO
        return min;
    }
}
