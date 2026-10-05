// Example 2: reading a whole file, as one String or as a list of lines.
// Run it from the examples folder with:  java Ex02ReadingText.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.List;

public class Ex02ReadingText {
    public static void main(String[] args) {
        Path file = Path.of("data", "poem.txt");      // data/poem.txt (works on Windows too)

        try {
            String everything = Files.readString(file);
            System.out.println("The file has " + everything.length() + " characters.");

            List<String> lines = Files.readAllLines(file);
            System.out.println("It has " + lines.size() + " lines. Numbered:");
            for (int i = 0; i < lines.size(); i++) {
                System.out.printf("%2d | %s%n", i + 1, lines.get(i));
            }
        } catch (NoSuchFileException e) {
            System.out.println("No such file: " + e.getMessage() + ". Are you in the examples folder?");
        } catch (IOException e) {
            System.out.println("Couldn't read the file: " + e.getMessage());
        }

        System.out.println("Does data/missing.txt exist? " + Files.exists(Path.of("data", "missing.txt")));
    }
}
