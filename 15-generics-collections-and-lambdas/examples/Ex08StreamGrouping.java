// Example 8: collecting stream results into maps: grouping, counting, averaging.
// Run it with:  java Ex08StreamGrouping.java

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Ex08StreamGrouping {

    record Student(String name, String programme, int level, double gpa) { }

    public static void main(String[] args) {
        List<Student> students = List.of(
                new Student("Akua", "BME", 300, 3.85), new Student("Kwesi", "EEE", 200, 3.10),
                new Student("Nii", "CSC", 100, 2.40), new Student("Esi", "BME", 200, 3.55),
                new Student("Kofi", "CSC", 200, 1.95), new Student("Yaa", "EEE", 300, 3.85),
                new Student("Selorm", "EEE", 100, 2.70));

        // Group into a Map<programme, List<Student>>
        Map<String, List<Student>> byProgramme = students.stream()
                .collect(Collectors.groupingBy(Student::programme, TreeMap::new, Collectors.toList()));
        byProgramme.forEach((prog, list) -> System.out.println(prog + ": " + list.size() + " students"));

        // Count per group
        Map<Integer, Long> perLevel = students.stream()
                .collect(Collectors.groupingBy(Student::level, TreeMap::new, Collectors.counting()));
        System.out.println("Per level: " + perLevel);

        // Average per group
        Map<String, Double> avgGpa = students.stream()
                .collect(Collectors.groupingBy(Student::programme, TreeMap::new, Collectors.averagingDouble(Student::gpa)));
        avgGpa.forEach((p, a) -> System.out.printf("%s average GPA %.2f%n", p, a));

        // All the statistics at once
        DoubleSummaryStatistics stats = students.stream().mapToDouble(Student::gpa).summaryStatistics();
        System.out.printf("GPA: min %.2f, max %.2f, average %.2f over %d students%n",
                stats.getMin(), stats.getMax(), stats.getAverage(), stats.getCount());

        // Optional: a result that might not exist (an empty list has no maximum)
        Optional<Student> best400 = students.stream().filter(s -> s.level() == 400)
                .max((a, b) -> Double.compare(a.gpa(), b.gpa()));
        System.out.println("Best level 400 student: " + best400.map(Student::name).orElse("(none)"));

        // Join names into one String
        System.out.println(students.stream().map(Student::name).sorted().collect(Collectors.joining(", ")));

        // Split into two groups with partitioningBy
        Map<Boolean, List<String>> honours = students.stream().collect(Collectors.partitioningBy(
                s -> s.gpa() >= 3.5, Collectors.mapping(Student::name, Collectors.toList())));
        System.out.println("Honours: " + honours.get(true) + ", others: " + honours.get(false));
    }
}
