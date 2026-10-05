package com.makerspace.makerstore.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.makerspace.makerstore.model.Component;
import com.makerspace.makerstore.model.Inventory;
import com.makerspace.makerstore.model.Kit;
import com.makerspace.makerstore.model.Tool;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StorageTest {

    @TempDir                     // JUnit gives each test a fresh, empty folder, deleted afterwards
    Path folder;

    @Test
    void saveAndLoadKeepsEveryItem() throws Exception {
        Inventory inv = new Inventory();
        inv.add(new Tool("T01", "Soldering iron", "Tools", 210, "good"));
        inv.add(new Component("C01", "LED pack", "Components", 15.5, 40, 10));
        Kit kit = new Kit("K01", "Robot kit", "Kits");
        kit.add(new Component("C02", "Servo", "Motors", 45, 2, 0));
        inv.add(kit);

        Path file = folder.resolve("save.json");
        Storage.save(inv, file);
        Inventory loaded = Storage.load(file);

        assertEquals(inv.all(), loaded.all());
        assertEquals(Kit.class, loaded.find("K01").getClass());
    }

    @Test
    void missingFileGivesAnEmptyInventory() throws Exception {
        assertEquals(0, Storage.load(folder.resolve("nothing.json")).all().size());
    }

    // TODO: a corrupt file, CSV import with bad lines, CSV export
}
