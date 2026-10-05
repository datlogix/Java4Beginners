package com.makerspace.makerstore.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.makerspace.makerstore.model.Inventory;
import com.makerspace.makerstore.model.Component;
import com.makerspace.makerstore.model.Item;
import com.makerspace.makerstore.model.Kit;
import com.makerspace.makerstore.model.Tool;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/** Saving and loading: the whole inventory as JSON, and CSV import and export. */
public class Storage {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapterFactory(new SubtypeAdapterFactory<>(Item.class)
                    .register("Tool", Tool.class)
                    .register("Component", Component.class)
                    .register("Kit", Kit.class))
            .create();

    /** Saves everything. Writes to a temporary file first, then moves it into place,
     *  so a crash half way through can never leave a broken save file. */
    public static void save(Inventory inventory, Path file) throws IOException {
        Path temp = file.resolveSibling(file.getFileName() + ".tmp");
        Files.writeString(temp, GSON.toJson(inventory));
        Files.move(temp, file, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    /** Loads everything, or returns an empty Inventory if there's no save file yet.
     *  TODO: a CORRUPT file (Gson throws JsonParseException) must not crash the app. */
    public static Inventory load(Path file) throws IOException {
        if (!Files.exists(file)) {
            return new Inventory();
        }
        return GSON.fromJson(Files.readString(file), Inventory.class);
    }

    /** Imports tools and components from a CSV file like sample_data/stock.csv.
     *  Returns the problems found (one per bad line); good lines are added. */
    public static List<String> importStock(Inventory inventory, Path csv) throws IOException {
        List<String> problems = new ArrayList<>();
        // TODO (Module 14): kind,id,name,category,price,quantity,reorder_level
        return problems;
    }

    // TODO: exportStockReport(inventory, csv) and exportOverdue(inventory, today, csv)
}
