// Run it from the biomedical_starter folder with:
//     javac -d out *.java
//     java -cp out BioApp

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BioApp {
    public static void main(String[] args) throws IOException {
        String sequence = Files.readString(Path.of("data", "sequence.txt")).replace("\n", "").replace("\r", "");
        System.out.println("Loaded " + sequence.length() + " bases.");
        // TODO: the checks, the restriction-site search, the patient sort and the
        //       search timing experiment described in the README
    }
}
