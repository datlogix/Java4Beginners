// Run it from the generic_starter folder with:
//     javac -d out *.java
//     java -cp out FilmClubApp

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FilmClubApp {
    private static final Scanner IN = new Scanner(System.in);
    private static final Path DATA = Path.of("data", "movies.csv");

    public static void main(String[] args) throws IOException {
        List<Movie> movies = new ArrayList<>();
        List<String> lines = Files.readAllLines(DATA);
        for (String line : lines.subList(1, lines.size())) {
            movies.add(Movie.fromCsv(line));
        }
        MovieQueries queries = new MovieQueries(movies);
        System.out.println("Loaded " + movies.size() + " films.");

        // TODO: a menu offering every query in MovieQueries. Show long results with
        //       Page: 10 at a time, with "n" for the next page and "q" to stop.
    }
}
