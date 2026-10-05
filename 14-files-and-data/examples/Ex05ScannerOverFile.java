// Example 5: a Scanner can read a file just like it reads the keyboard.
// Run it from the examples folder with:  java Ex05ScannerOverFile.java

import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

public class Ex05ScannerOverFile {
    public static void main(String[] args) {
        // data/readings.txt holds numbers separated by spaces and new lines
        try (Scanner file = new Scanner(Path.of("data", "readings.txt"))) {
            double total = 0;
            int count = 0;
            while (file.hasNextDouble()) {        // is there another number to read?
                total += file.nextDouble();
                count++;
            }
            System.out.printf("%d readings, average %.2f%n", count, total / count);
        } catch (IOException e) {
            System.out.println("Couldn't open the file: " + e.getMessage());
        }
    }
}
