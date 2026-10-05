/** Anything that can be played: it has a title, a genre and a length. */
public interface Playable {
    String title();
    Genre genre();
    int durationSeconds();

    /** e.g. "3:05", or "1:02:05" for an hour or more. */
    default String durationText() {
        // TODO
        return durationSeconds() + " s";
    }
}
