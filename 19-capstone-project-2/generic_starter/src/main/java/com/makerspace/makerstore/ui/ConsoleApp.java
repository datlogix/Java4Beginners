package com.makerspace.makerstore.ui;

import com.makerspace.makerstore.model.Inventory;
import com.makerspace.makerstore.model.MakerStoreException;
import com.makerspace.makerstore.storage.Storage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/** The text menu. The ONLY class that reads the keyboard or prints for the user. */
public class ConsoleApp {
    private static final Path SAVE_FILE = Path.of("makerstore.json");
    private final Scanner in = new Scanner(System.in);
    private Inventory inventory;

    public void run() {
        try {
            inventory = Storage.load(SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Couldn't load the save file (" + e.getMessage() + "). Starting empty.");
            inventory = new Inventory();
        }
        System.out.println("=== MAKERSTORE ===  " + inventory.all().size() + " items");

        String choice;
        do {
            System.out.println();
            System.out.println("1) List items  2) Find an item  3) Lend a tool  4) Return a tool  5) Overdue");
            System.out.println("6) Low stock  7) Import stock CSV  8) Reports and charts  9) Quit");
            System.out.print("Choose: ");
            choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> inventory.all().forEach(System.out::println);
                    case "2" -> {
                        System.out.print("Item ID: ");
                        System.out.println(inventory.find(in.nextLine().trim()));
                    }
                    case "9" -> System.out.println("Goodbye.");
                    // TODO: every other option
                    default -> System.out.println("Please choose 1-9.");
                }
                save();     // save after every action, so nothing is ever lost
            } catch (MakerStoreException | IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("9"));
    }

    private void save() {
        try {
            Storage.save(inventory, SAVE_FILE);
        } catch (IOException e) {
            System.out.println("WARNING: couldn't save: " + e.getMessage());
        }
    }
}
