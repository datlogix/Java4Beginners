// Exercise 2: Songs and a Playlist.
//
// Write two classes that work together. A Playlist HOLDS a list of Songs.
//
// Song
//   - private final fields: title, artist, seconds (the length)
//   - the constructor rejects (IllegalArgumentException) an empty title or
//     artist, and a length that isn't between 1 and 3600 seconds
//   - getters for all three fields
//   - toString() gives e.g.  "Ye Ye Ye - Kwesi Arthur (3:05)"
//     (minutes:seconds, with the seconds always two digits)
//
// Playlist
//   - a private final name, and a private final List<Song>
//   - add(Song song) rejects a song that's already there: same title AND
//     artist, ignoring capitals
//   - remove(String title) removes the first song with that title (any
//     capitals) and returns true, or returns false if there isn't one
//   - size(), totalSeconds(), and totalTime() which gives e.g. "11:42"
//     (or "1:02:05" once it's an hour or more)
//   - longest() returns the longest Song, or null if the playlist is empty
//   - byArtist(String artist) returns a NEW List<Song> of that artist's songs
//   - toString() lists the name, the number of songs and the total time
//
// main() already contains the checks. Fill in the classes until they all pass.
//
// Run it with:  java Exercise2.java

import java.util.ArrayList;
import java.util.List;

public class Exercise2 {

    static class Song {
        // TODO
        Song(String title, String artist, int seconds) {
        }

        String getTitle() { return ""; }
        String getArtist() { return ""; }
        int getSeconds() { return 0; }
    }

    static class Playlist {
        // TODO
        Playlist(String name) {
        }

        void add(Song song) {
        }

        boolean remove(String title) {
            return false;
        }

        int size() { return 0; }
        int totalSeconds() { return 0; }
        String totalTime() { return ""; }
        Song longest() { return null; }
        List<Song> byArtist(String artist) { return new ArrayList<>(); }
    }

    // ---------------- Don't change anything below this line ----------------

    static int passed = 0, failed = 0;

    static void check(String description, Object actual, Object expected) {
        if (String.valueOf(actual).equals(String.valueOf(expected))) {
            passed++;
            System.out.println("PASS  " + description);
        } else {
            failed++;
            System.out.println("FAIL  " + description + ": got " + actual + ", expected " + expected);
        }
    }

    static boolean refuses(Runnable action) {
        try {
            action.run();
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    public static void main(String[] args) {
        Song a = new Song("Ye Ye Ye", "Kwesi Arthur", 185);
        Song b = new Song("Kwaku the Traveller", "Black Sherif", 189);
        Song c = new Song("Second Sermon", "Black Sherif", 158);
        Song d = new Song("Sore", "Yaw Tog", 170);
        check("Song toString", a, "Ye Ye Ye - Kwesi Arthur (3:05)");
        check("Song with 2:38", c, "Second Sermon - Black Sherif (2:38)");
        check("empty title refused", refuses(() -> new Song("", "X", 100)), true);
        check("0 seconds refused", refuses(() -> new Song("T", "X", 0)), true);
        check("3601 seconds refused", refuses(() -> new Song("T", "X", 3601)), true);

        Playlist p = new Playlist("Road Trip");
        p.add(a);
        p.add(b);
        p.add(c);
        p.add(d);
        check("size after 4 adds", p.size(), 4);
        check("duplicate (any capitals) refused",
                refuses(() -> p.add(new Song("YE YE YE", "kwesi arthur", 185))), true);
        check("total seconds", p.totalSeconds(), 702);
        check("total time", p.totalTime(), "11:42");
        check("longest", p.longest(), b);
        check("byArtist Black Sherif", p.byArtist("Black Sherif").size(), 2);
        check("remove sore (any capitals)", p.remove("sore"), true);
        check("remove missing song", p.remove("Nothing"), false);
        check("size after remove", p.size(), 3);
        check("empty playlist's longest is null", new Playlist("Empty").longest(), null);
        Playlist marathon = new Playlist("Marathon");
        for (int i = 1; i <= 20; i++) {
            marathon.add(new Song("Track " + i, "DJ", 3600));
        }
        check("total time over an hour", marathon.totalTime(), "20:00:00");
        System.out.println();
        System.out.println("Your toString: " + p);
        System.out.println(passed + " passed, " + failed + " failed.");
    }
}
