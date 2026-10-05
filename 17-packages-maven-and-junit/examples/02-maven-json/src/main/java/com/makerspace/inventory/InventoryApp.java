package com.makerspace.inventory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Saves a list of items as JSON, then loads it back, using the Gson library.
 * Maven downloads Gson automatically because it's listed in pom.xml.
 *
 * Run it from the 02-maven-json folder with:
 *     ./mvnw -q compile exec:java          (Windows: mvnw -q compile exec:java)
 */
public class InventoryApp {
    public static void main(String[] args) throws IOException {
        List<Item> stock = List.of(
                new Item("A01", "Arduino Uno", 12, 180.0),
                new Item("L05", "LED pack", 40, 15.5));

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(stock);
        Path file = Path.of("stock.json");
        Files.writeString(file, json);
        System.out.println("Saved " + file + ":");
        System.out.println(json);

        Type listOfItems = new TypeToken<List<Item>>() { }.getType();
        List<Item> loaded = gson.fromJson(Files.readString(file), listOfItems);
        System.out.println("Loaded back " + loaded.size() + " items. Same as before? " + loaded.equals(stock));
    }
}
