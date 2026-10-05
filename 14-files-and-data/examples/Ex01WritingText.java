// Example 1: writing text to a file in one line.
// Run it with:  java Ex01WritingText.java   ...then open greeting.txt in VS Code.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Ex01WritingText {
    public static void main(String[] args) throws IOException {
        Path file = Path.of("greeting.txt");          // a Path says WHERE a file is

        Files.writeString(file, "Akwaaba!\nWelcome to file handling.\n");
        System.out.println("Wrote " + Files.size(file) + " bytes to " + file.toAbsolutePath());

        // write() takes a list of lines and adds the line endings for you
        List<String> shopping = List.of("rice", "tomatoes", "onions", "palm oil");
        Files.write(Path.of("shopping.txt"), shopping);
        System.out.println("Wrote " + shopping.size() + " lines to shopping.txt");

        // Writing again REPLACES the old contents completely
        Files.writeString(file, "Replaced.\n");
        System.out.println("greeting.txt now says: " + Files.readString(file).trim());
    }
}
