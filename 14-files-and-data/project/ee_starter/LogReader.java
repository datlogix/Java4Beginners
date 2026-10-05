import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Reads a bench log CSV, skipping (and remembering) broken rows. */
public class LogReader {
    private final List<String> problems = new ArrayList<>();

    public List<Sample> read(Path file) throws IOException {
        problems.clear();
        // TODO: use a BufferedReader in try-with-resources, reading line by line
        return new ArrayList<>();
    }

    public List<String> getProblems() {
        return new ArrayList<>(problems);
    }
}
