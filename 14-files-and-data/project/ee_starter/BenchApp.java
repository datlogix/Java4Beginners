// Run it from the ee_starter folder with:
//     javac -d out *.java
//     java -cp out BenchApp
// or analyse a different log:  java -cp out BenchApp path/to/log.csv

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class BenchApp {
    public static final double CURRENT_LIMIT = 1.5;

    public static void main(String[] args) {
        Path input = Path.of(args.length > 0 ? args[0] : "data/bench_log.csv");
        LogReader reader = new LogReader();
        try {
            List<Sample> samples = reader.read(input);
            // TODO: report the skipped rows, analyse, print a summary, write
            //       output/summary.txt and output/with_power.csv (create the
            //       output folder if it's missing)
        } catch (IOException e) {
            System.out.println("Couldn't read " + input + ": " + e.getMessage());
        }
    }
}
