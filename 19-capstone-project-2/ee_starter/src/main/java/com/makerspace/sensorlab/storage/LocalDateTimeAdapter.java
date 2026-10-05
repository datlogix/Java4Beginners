package com.makerspace.sensorlab.storage;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/** Teaches Gson to write a LocalDateTime as "2026-10-04T09:30" and read it back. (Given.) */
public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    @Override
    public void write(JsonWriter out, LocalDateTime time) throws IOException {
        if (time == null) {
            out.nullValue();
        } else {
            out.value(time.toString());
        }
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return LocalDateTime.parse(in.nextString());
    }
}
