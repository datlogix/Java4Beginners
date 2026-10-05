// Example 0: the hook. One list of results, sorted five different ways,
// each with ONE line of code.
// Run it with:  java Ex00Leaderboard.java

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Ex00Leaderboard {

    enum School { ACHIMOTA, PRESEC, WESLEY_GIRLS, MFANTSIPIM, ST_PETERS }

    record Result(String name, School school, int score, double seconds) { }

    public static void main(String[] args) {
        List<Result> results = new ArrayList<>(List.of(
                new Result("Ama", School.WESLEY_GIRLS, 88, 41.2),
                new Result("Kojo", School.PRESEC, 92, 47.9),
                new Result("Efua", School.ACHIMOTA, 88, 39.5),
                new Result("Yaw", School.MFANTSIPIM, 75, 35.0),
                new Result("Akosua", School.ACHIMOTA, 92, 44.1),
                new Result("Kwame", School.ST_PETERS, 81, 52.3)));

        show("By score, highest first", results, Comparator.comparingInt(Result::score).reversed());
        show("By name", results, Comparator.comparing(Result::name));
        show("Fastest first", results, Comparator.comparingDouble(Result::seconds));
        show("By school, then score", results,
                Comparator.comparing(Result::school).thenComparing(Result::score, Comparator.reverseOrder()));
        show("Score, then fastest as the tie-breaker", results,
                Comparator.comparingInt(Result::score).reversed().thenComparingDouble(Result::seconds));
    }

    static void show(String title, List<Result> results, Comparator<Result> order) {
        results.sort(order);
        System.out.println(title + ":");
        for (Result r : results) {
            System.out.printf("  %-7s %-13s %3d  %5.1f s%n", r.name(), r.school(), r.score(), r.seconds());
        }
        System.out.println();
    }
}
