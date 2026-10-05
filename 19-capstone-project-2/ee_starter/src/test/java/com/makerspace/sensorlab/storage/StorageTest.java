package com.makerspace.sensorlab.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.makerspace.sensorlab.model.Lab;
import com.makerspace.sensorlab.model.VoltageSensor;
import java.nio.file.Path;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StorageTest {

    @TempDir
    Path folder;

    @Test
    void saveAndLoadKeepsSensorsAndReadings() throws Exception {
        Lab lab = new Lab();
        lab.addSensor(new VoltageSensor("V5", "5 V rail", 4.75, 5.25));
        lab.record("V5", LocalDateTime.of(2026, 10, 4, 9, 0), 5.01);
        Path file = folder.resolve("lab.json");
        Storage.save(lab, file);
        Lab loaded = Storage.load(file);
        assertEquals(lab.getSensors(), loaded.getSensors());
        assertEquals(lab.getReadings(), loaded.getReadings());
        assertEquals(VoltageSensor.class, loaded.sensor("V5").getClass());
    }

    // TODO: a corrupt file, importing the messy sample log, exports
}
