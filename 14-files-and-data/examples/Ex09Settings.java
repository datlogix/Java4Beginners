// Example 9: key=value settings files, read and written with Properties.
// Run it with:  java Ex09Settings.java   (run it twice: the second run remembers)

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class Ex09Settings {
    static final Path FILE = Path.of("settings.properties");

    public static void main(String[] args) throws IOException {
        Properties settings = new Properties();
        if (Files.exists(FILE)) {
            try (Reader reader = Files.newBufferedReader(FILE)) {
                settings.load(reader);
            }
        }

        // getProperty gives a default if the key is missing, like getOrDefault
        String theme = settings.getProperty("theme", "light");
        int runs = Integer.parseInt(settings.getProperty("runs", "0")) + 1;
        System.out.println("Theme: " + theme + ", run number " + runs);

        settings.setProperty("runs", String.valueOf(runs));
        settings.setProperty("theme", theme.equals("light") ? "dark" : "light");    // switch for next time
        try (Writer writer = Files.newBufferedWriter(FILE)) {
            settings.store(writer, "Settings for Ex09");
        }
        System.out.println("Saved. Open " + FILE + " to see the format.");
    }
}
