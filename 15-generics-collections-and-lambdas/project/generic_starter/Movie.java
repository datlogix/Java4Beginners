// Module 15 Project, Track A: Film Club Explorer
// Author: YOUR NAME

/** One film in the club's catalogue. */
public record Movie(String title, int year, String genre, int runtimeMinutes, double rating, String country) {

    public static Movie fromCsv(String line) {
        String[] f = line.split(",");
        if (f.length != 6) {
            throw new IllegalArgumentException("expected 6 fields, found " + f.length);
        }
        return new Movie(f[0], Integer.parseInt(f[1]), f[2], Integer.parseInt(f[3]),
                Double.parseDouble(f[4]), f[5]);
    }

    public int decade() {
        return year / 10 * 10;
    }

    @Override
    public String toString() {
        return String.format("%-24s %d  %-11s %3d min  %.1f  %s", title, year, genre, runtimeMinutes, rating, country);
    }
}
