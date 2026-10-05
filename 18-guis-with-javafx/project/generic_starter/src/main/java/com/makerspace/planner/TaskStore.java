package com.makerspace.planner;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Loads and saves tasks as CSV (Module 14). The window never touches the file directly. */
public class TaskStore {
    private final Path file;

    public TaskStore(Path file) {
        this.file = file;
    }

    public List<Task> load() throws IOException {
        return new ArrayList<>();   // TODO (a missing file means no tasks yet)
    }

    public void save(List<Task> tasks) throws IOException {
        // TODO
    }
}
