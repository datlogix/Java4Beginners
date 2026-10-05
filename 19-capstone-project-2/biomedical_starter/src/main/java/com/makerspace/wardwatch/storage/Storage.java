package com.makerspace.wardwatch.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makerspace.wardwatch.model.HeartRate;
import com.makerspace.wardwatch.model.Measurement;
import com.makerspace.wardwatch.model.Ward;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/** Saving and loading: the whole ward as JSON, and CSV import and export. */
public class Storage {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .registerTypeAdapterFactory(new SubtypeAdapterFactory<>(Measurement.class)
                    .register("HeartRate", HeartRate.class))
                    // TODO: register BloodPressure, Temperature and SpO2 when you've written them
            .create();

    public static void save(Ward ward, Path file) throws IOException {
        Path temp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.writeString(temp, GSON.toJson(ward));
        Files.move(temp, file, StandardCopyOption.REPLACE_EXISTING);
    }

    /** Loads the ward, or returns null if there's no save file yet.
     *  TODO: a CORRUPT file (JsonParseException) must not crash the app. */
    public static Ward load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return null;
        }
        return GSON.fromJson(Files.readString(file), Ward.class);
    }

    /** Imports observations like sample_data/ward_vitals.csv (patient_id,time,type,value),
     *  where type is HR, BP (value like 120/80), TEMP or SPO2. Returns one message per bad row. */
    public static List<String> importVitals(Ward ward, Path csv) throws IOException {
        List<String> problems = new ArrayList<>();
        // TODO (Modules 14 and 15)
        return problems;
    }

    // TODO: exportAlertLog(ward, csv) and a discharge summary (text) for one patient
}
