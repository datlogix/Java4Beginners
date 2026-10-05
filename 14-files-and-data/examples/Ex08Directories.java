// Example 8: folders, listing files, and where "here" is.
// Run it with:  java Ex08Directories.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class Ex08Directories {
    public static void main(String[] args) throws IOException {
        // Relative paths start from the WORKING DIRECTORY: the folder you ran java from.
        System.out.println("Working directory: " + Path.of("").toAbsolutePath());

        Path folder = Path.of("output", "reports", "2026");
        Files.createDirectories(folder);                  // makes every missing folder in the path
        for (String name : List.of("january.txt", "february.txt", "march.txt")) {
            Files.writeString(folder.resolve(name), "Report: " + name + "\n");   // resolve: folder + file
        }

        System.out.println("Files in " + folder + ":");
        try (Stream<Path> entries = Files.list(folder)) {
            entries.sorted().forEach(p -> System.out.println("  " + p.getFileName()));
        }

        Path march = folder.resolve("march.txt");
        System.out.println("Parent of march.txt: " + march.getParent());
        System.out.println("Is it a directory? " + Files.isDirectory(march));
        Files.delete(march);                               // gone for good: there's no recycle bin!
        System.out.println("march.txt exists after delete? " + Files.exists(march));
    }
}
