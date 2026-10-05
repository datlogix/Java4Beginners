// Module 12 Project, Track A: Media Library
// Author: YOUR NAME

/** The kinds of media in the library. */
public enum Genre {
    AFROBEATS("Afrobeats"),
    HIGHLIFE("Highlife"),
    GOSPEL("Gospel");
    // TODO: at least three more genres, including some for podcasts and audiobooks

    private final String displayName;

    Genre(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
