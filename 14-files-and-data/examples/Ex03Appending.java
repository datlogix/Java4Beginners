// Example 3: APPENDING adds to the end of a file instead of replacing it.
// Perfect for logs. Run it several times, then open lab-log.txt.
// Run it with:  java Ex03Appending.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

public class Ex03Appending {
    public static void main(String[] args) throws IOException {
        Path log = Path.of("lab-log.txt");
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        double reading = 20 + new Random().nextDouble() * 5;

        String line = String.format("%s  temperature %.2f C%n", time, reading);
        // CREATE: make the file if it's missing.  APPEND: add to the end.
        Files.writeString(log, line, StandardOpenOption.CREATE, StandardOpenOption.APPEND);

        System.out.println("Logged: " + line.trim());
        System.out.println("The log now has " + Files.readAllLines(log).size() + " line(s).");
    }
}
