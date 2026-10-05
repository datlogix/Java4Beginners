// Example 4: reading and writing line by line with a BufferedReader and a
// PrintWriter, opened in try-with-resources so they're ALWAYS closed.
// Run it from the examples folder with:  java Ex04TryWithResources.java

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

public class Ex04TryWithResources {
    public static void main(String[] args) {
        Path input = Path.of("data", "poem.txt");
        Path output = Path.of("poem-shouted.txt");

        // Everything opened in the brackets after try is closed automatically at the end,
        // even if an exception is thrown. No finally needed.
        try (BufferedReader reader = Files.newBufferedReader(input);
             PrintWriter writer = new PrintWriter(Files.newBufferedWriter(output))) {

            String line;
            int count = 0;
            while ((line = reader.readLine()) != null) {      // readLine gives null at the end
                writer.println(line.toUpperCase());           // PrintWriter has println and printf!
                count++;
            }
            writer.printf("(%d lines, shouted)%n", count);
            System.out.println("Wrote " + output + " (" + count + " lines)");

        } catch (IOException e) {
            System.out.println("File problem: " + e.getMessage());
        }
        // Reading line by line uses very little memory, so it works even for
        // files far too big for readAllLines.
    }
}
