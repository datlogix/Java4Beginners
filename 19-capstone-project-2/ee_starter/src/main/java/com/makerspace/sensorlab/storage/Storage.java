package com.makerspace.sensorlab.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makerspace.sensorlab.model.Lab;
import com.makerspace.sensorlab.model.Sensor;
import com.makerspace.sensorlab.model.TemperatureSensor;
import com.makerspace.sensorlab.model.VoltageSensor;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Saving and loading: the whole lab as JSON, and CSV import and export. */
public class Storage {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapterFactory(new SubtypeAdapterFactory<>(Sensor.class)
                    .register("VoltageSensor", VoltageSensor.class)
                    .register("TemperatureSensor", TemperatureSensor.class))
                    // TODO: register CurrentSensor when you've written it
            .create();

    /** Saves everything, via a temporary file so a crash can't leave a broken save. */
    public static void save(Lab lab, Path file) throws IOException {
        Path temp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.writeString(temp, GSON.toJson(lab));
        Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    /** Loads the lab, or returns null if there's no save file yet.
     *  TODO: a CORRUPT file (JsonParseException) must not crash the app. */
    public static Lab load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return null;
        }
        return GSON.fromJson(Files.readString(file), Lab.class);
    }

    /** Imports a logger file like sample_data/bench_log.csv (timestamp,sensor_id,raw),
     *  recording each good row through the lab. Returns one message per bad row. */
    public static List<String> importLog(Lab lab, Path csv) throws IOException {
        List<String> problems = new ArrayList<>();
        // TODO (Modules 14 and 15): skip and report bad rows: missing fields, bad numbers,
        //      bad timestamps, unknown sensors, raw values a sensor can't produce
        return problems;
    }

    // TODO: exportReadings(lab, csv) and exportAlarms(lab, csv)
}
