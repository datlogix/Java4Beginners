// Run it from the biomedical_starter folder with:
//     javac -d out *.java
//     java -cp out ClinicApp

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ClinicApp {
    private static final Path DATA = Path.of("data", "visits.csv");

    public static void main(String[] args) throws IOException {
        List<Visit> visits = new ArrayList<>();
        List<String> lines = Files.readAllLines(DATA);
        for (String line : lines.subList(1, lines.size())) {
            visits.add(Visit.fromCsv(line));
        }
        System.out.println("Loaded " + visits.size() + " visits.");

        // TODO: the report and the queue simulation described in the README
    }
}
