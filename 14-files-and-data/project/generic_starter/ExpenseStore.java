import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/** Loads and saves expenses in a CSV file, and remembers any lines it had to skip. */
public class ExpenseStore {
    private final Path file;
    private final List<String> problems = new ArrayList<>();

    public ExpenseStore(Path file) {
        this.file = file;
    }

    /** Reads every good line. A missing file just means "no expenses yet". */
    public List<Expense> load() throws IOException {
        problems.clear();
        // TODO: skip the header; for a broken line, add "line N: reason" to problems
        return new ArrayList<>();
    }

    /** Writes every expense, with a header line, REPLACING the file. */
    public void save(List<Expense> expenses) throws IOException {
        // TODO
    }

    public List<String> getProblems() {
        return new ArrayList<>(problems);
    }
}
