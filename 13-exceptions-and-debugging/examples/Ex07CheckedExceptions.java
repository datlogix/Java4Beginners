// Example 7: checked exceptions. Some exceptions (like IOException) must be
// handled or declared with "throws", or the code won't compile.
// Run it from the examples folder with:  java Ex07CheckedExceptions.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex07CheckedExceptions {

    // "throws IOException" passes the duty to handle it on to whoever calls this
    static String firstLine(String fileName) throws IOException {
        return Files.readAllLines(Path.of(fileName)).get(0);
    }

    // Thread.sleep throws the checked InterruptedException: that's why Module 5
    // had to add "throws InterruptedException" to main.
    static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            System.out.println("Woken up early!");
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println(firstLine("Ex07CheckedExceptions.java"));
            System.out.println(firstLine("no-such-file.txt"));
        } catch (IOException e) {
            System.out.println("Couldn't read the file: " + e);
        }
        pause(200);
        System.out.println("Done.");
        // firstLine("x");   // remove the // to see: unreported exception IOException; must be caught or declared
    }
}
