// Example 0: the hook. A program that REMEMBERS you, even after it ends.
// Run it with:  java Ex00Remember.java   ...then run it again. And again.
// To make it forget you, delete memory.txt.

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Ex00Remember {
    static final Path MEMORY = Path.of("memory.txt");

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        String now = LocalDateTime.now().format(DateTimeFormatter.ofPattern("d MMM yyyy 'at' HH:mm"));

        if (Files.exists(MEMORY)) {
            List<String> lines = Files.readAllLines(MEMORY);
            String name = lines.get(0);
            int visits = Integer.parseInt(lines.get(1)) + 1;
            System.out.println("Welcome back, " + name + "!");
            System.out.println("This is visit number " + visits + ". Last time was " + lines.get(2) + ".");
            Files.write(MEMORY, List.of(name, String.valueOf(visits), now));
        } else {
            System.out.print("Hello! I don't think we've met. What's your name? ");
            String name = in.nextLine().trim();
            Files.write(MEMORY, List.of(name, "1", now));
            System.out.println("Nice to meet you, " + name + ". I'll remember you.");
        }
        System.out.println("(Everything I know is in " + MEMORY.toAbsolutePath() + ")");
    }
}
