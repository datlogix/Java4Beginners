// Run it from the ee_starter folder with:
//     javac -d out *.java
//     java -cp out InventoryApp

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

public class InventoryApp {
    private static final Scanner IN = new Scanner(System.in);
    private static final Path DATA = Path.of("data", "parts.csv");

    public static void main(String[] args) throws IOException {
        Inventory<Part> store = new Inventory<>();
        List<String> lines = Files.readAllLines(DATA);
        for (String line : lines.subList(1, lines.size())) {
            store.add(Part.fromCsv(line));
        }
        System.out.println("Loaded " + store.all().size() + " parts.");

        // TODO: a menu of queries (see the README): total value, value by type,
        //       reorder list, resistors in a range, cheapest of each type, a location's
        //       contents, find by part number...
    }
}
