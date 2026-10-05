// Run it from the biomedical_starter folder with:
//     javac -d out *.java
//     java -cp out VitalsApp
//
// Thresholds are simplified for teaching. This is not a clinical tool.

import java.io.IOException;
import java.nio.file.Path;

public class VitalsApp {
    public static void main(String[] args) {
        VitalsLog log = new VitalsLog();
        try {
            log.load(Path.of("data", "vitals_log.csv"));
            // TODO: report skipped rows; print a summary table per patient (readings,
            //       average HR, lowest SpO2, highest temperature, number of abnormal
            //       readings); write output/alerts.csv and output/summary.txt
        } catch (IOException e) {
            System.out.println("Couldn't read the log: " + e.getMessage());
        }
    }
}
