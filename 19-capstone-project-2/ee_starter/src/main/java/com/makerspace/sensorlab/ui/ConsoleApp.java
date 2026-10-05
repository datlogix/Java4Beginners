package com.makerspace.sensorlab.ui;

import com.makerspace.sensorlab.model.Lab;
import com.makerspace.sensorlab.model.SensorLabException;
import com.makerspace.sensorlab.model.TemperatureSensor;
import com.makerspace.sensorlab.model.VoltageSensor;
import com.makerspace.sensorlab.storage.Storage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Scanner;

/** The text menu. The ONLY class that reads the keyboard or prints for the user. */
public class ConsoleApp {
    private static final Path SAVE_FILE = Path.of("sensorlab.json");
    private final Scanner in = new Scanner(System.in);
    private Lab lab;

    public void run() {
        try {
            lab = Storage.load(SAVE_FILE);
        } catch (IOException e) {
            System.out.println("Couldn't load the save file (" + e.getMessage() + ").");
        }
        if (lab == null) {
            lab = defaultBench();
        }
        System.out.println("=== SENSORLAB ===  " + lab.getSensors().size() + " sensors, "
                + lab.getReadings().size() + " readings");

        String choice;
        do {
            System.out.println();
            System.out.println("1) Sensors  2) Sensor statistics  3) Alarms  4) Import a log  5) Simulate a session");
            System.out.println("6) Calibrate a sensor  7) Reports and charts  8) Quit");
            System.out.print("Choose: ");
            choice = in.nextLine().trim();
            try {
                switch (choice) {
                    case "1" -> lab.getSensors().forEach(System.out::println);
                    case "2" -> {
                        System.out.print("Sensor ID: ");
                        System.out.println(lab.sensor(in.nextLine().trim()));
                        // TODO: its statistics
                    }
                    case "8" -> System.out.println("Goodbye.");
                    // TODO: every other option
                    default -> System.out.println("Please choose 1-8.");
                }
                save();
            } catch (SensorLabException | IllegalArgumentException e) {
                System.out.println("Sorry: " + e.getMessage());
            }
        } while (!choice.equals("8"));
    }

    /** The sensors on the bench (and in sample_data/bench_log.csv). */
    private static Lab defaultBench() {
        Lab lab = new Lab();
        lab.addSensor(new VoltageSensor("V5", "5 V rail", 4.75, 5.25));
        lab.addSensor(new VoltageSensor("V3", "3.3 V rail", 3.135, 3.465));
        lab.addSensor(new TemperatureSensor("T1", "Regulator temperature", 15, 60));
        // TODO: lab.addSensor(new CurrentSensor("I1", "Load current", 0, 1.5, 0.1));   (0.1 ohm shunt)
        return lab;
    }

    private void save() {
        try {
            Storage.save(lab, SAVE_FILE);
        } catch (IOException e) {
            System.out.println("WARNING: couldn't save: " + e.getMessage());
        }
    }
}
