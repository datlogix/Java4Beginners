package com.makerspace.wardwatch.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.makerspace.wardwatch.model.HeartRate;
import com.makerspace.wardwatch.model.Patient;
import com.makerspace.wardwatch.model.Ward;
import java.nio.file.Path;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class StorageTest {

    @TempDir
    Path folder;

    @Test
    void saveAndLoadKeepsPatientsAndMeasurements() throws Exception {
        Ward ward = new Ward("Ward 3B", 12);
        ward.admit(new Patient("P001", "Ama Owusu", 34), 3);
        ward.record("P001").add(new HeartRate(LocalDateTime.of(2026, 10, 4, 8, 0), 88));
        Path file = folder.resolve("ward.json");
        Storage.save(ward, file);
        Ward loaded = Storage.load(file);
        assertEquals("Ama Owusu", loaded.record("P001").getPatient().name());
        assertEquals(HeartRate.class, loaded.record("P001").getMeasurements().get(0).getClass());
        assertEquals(88, ((HeartRate) loaded.record("P001").getMeasurements().get(0)).getBpm());
    }

    // TODO: a corrupt file, importing the messy sample CSV, exports
}
