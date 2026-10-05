/** A song. A record: its data never changes once it's created. */
public record Song(String title, String artist, Genre genre, int durationSeconds) implements Playable {

    public Song {
        // TODO: reject an empty title or artist, a null genre, and a length outside 1-3600 seconds
    }

    @Override
    public String toString() {
        return title + " - " + artist + " (" + durationText() + ")";
    }
}
