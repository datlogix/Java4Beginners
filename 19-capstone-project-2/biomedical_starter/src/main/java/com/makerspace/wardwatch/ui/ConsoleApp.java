package com.makerspace.wardwatch.ui;

import com.makerspace.wardwatch.model.Ward;
import com.makerspace.wardwatch.model.WardWatchException;
import com.makerspace.wardwatch.storage.Storage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/**
 * The text menu. The ONLY class that reads the keyboard or prints for the user.
 * The early-warning score is simplified for teaching. This is not a clinical tool.
 */
public class ConsoleApp {
    private static final Path SAVE_FILE = Path.of("wardwatch.json");
    private final Scanner in = new Scanner(System.in);
    private Ward ward;

    public void run() {
        try {
            ward = Storage.load(SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Couldn't load the save file (" + e.getMessage() + ").");
        }
        if (ward == null) {
            ward = new Ward("Ward 3B", 12);
        }
        System.out.println("=== WARDWATCH ===  " + ward.getName() + ", " + ward.getPatients().size() + " patients");

        String choice;
        do {
            System.out.println();
            System.out.println("1) Ward overview  2) Patient details  3) Admit  4) Record vitals  5) Alerts");
            System.out.println("6) Import vitals CSV  7) Discharge  8) Reports and charts  9) Quit");
            System.out.print("Choose: ");
            choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "2" -> {
                        System.out.print("Patient ID: ");
                        System.out.println(ward.record(in.nextLine().trim()).getPatient());
                        // TODO: their measurements and score
                    }
                    case "9" -> System.out.println("Goodbye.");
                    // TODO: every other option
                    default -> System.out.println("Please choose 1-9.");
                }
                save();
            } catch (WardWatchException | IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("9"));
    }

    private void save() {
        try {
            Storage.save(ward, SAVE_FILE);
        } catch (IOException e) {
            System.out.println("WARNING: couldn't save: " + e.getMessage());
        }
    }
}
