// Example 7: writing objects out as CSV, and reading them back: a round trip.
// Run it with:  java Ex07WritingCSV.java   ...then open stock.csv (try it in a spreadsheet too).

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Ex07WritingCSV {

    record Product(String code, String name, int quantity, double price) {

        String toCsv() {
            return code + "," + name + "," + quantity + "," + price;
        }

        static Product fromCsv(String line) {
            String[] f = line.split(",");
            return new Product(f[0], f[1], Integer.parseInt(f[2]), Double.parseDouble(f[3]));
        }
    }

    static void save(List<Product> products, Path file) throws IOException {
        List<String> lines = new ArrayList<>();
        lines.add("code,name,quantity,price");            // a header line describes the columns
        for (Product p : products) {
            lines.add(p.toCsv());
        }
        Files.write(file, lines);
    }

    static List<Product> load(Path file) throws IOException {
        List<Product> products = new ArrayList<>();
        List<String> lines = Files.readAllLines(file);
        for (String line : lines.subList(1, lines.size())) {     // skip the header
            products.add(Product.fromCsv(line));
        }
        return products;
    }

    public static void main(String[] args) throws IOException {
        List<Product> stock = List.of(
                new Product("A01", "Arduino Uno", 12, 180.0),
                new Product("L05", "LED pack", 40, 15.5),
                new Product("S02", "Servo motor", 7, 45.0));

        Path file = Path.of("stock.csv");
        save(stock, file);
        System.out.println("Saved:\n" + Files.readString(file));

        List<Product> loaded = load(file);
        System.out.println("Loaded back " + loaded.size() + " products.");
        System.out.println("Identical to the originals? " + loaded.equals(stock));
    }
}
