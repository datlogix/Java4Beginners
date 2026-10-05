import java.util.List;
import java.util.Map;
import java.util.Optional;

/** Questions about the catalogue. Every method is ONE stream pipeline. */
public class MovieQueries {
    private final List<Movie> movies;

    public MovieQueries(List<Movie> movies) {
        this.movies = List.copyOf(movies);
    }

    /** Every film, best rated first, ties by title. */
    public List<Movie> byRating() {
        return List.of();   // TODO
    }

    /** Films whose title contains the text, ignoring capitals. */
    public List<Movie> search(String text) {
        return List.of();   // TODO
    }

    /** Films in a genre, newest first. */
    public List<Movie> inGenre(String genre) {
        return List.of();   // TODO
    }

    /** The number of films in each genre, in alphabetical order. */
    public Map<String, Long> countByGenre() {
        return Map.of();   // TODO
    }

    /** The average rating of each decade's films, in order. */
    public Map<Integer, Double> averageRatingByDecade() {
        return Map.of();   // TODO
    }

    /** The longest film from a country, if there is one. */
    public Optional<Movie> longestFrom(String country) {
        return Optional.empty();   // TODO
    }

    /** A "film night": the best-rated films, in rating order, that fit in the time
     *  available, taking each one only if it still fits. (A loop is fine here,
     *  after a stream has sorted the films.) */
    public List<Movie> filmNight(int minutesAvailable) {
        return List.of();   // TODO
    }
}
