// Example 7: an enum is a type with a FIXED set of named values.
// Run it with:  java Ex07Enums.java

public class Ex07Enums {

    enum Day { MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY }

    enum TrafficLight { RED, AMBER, GREEN }

    static boolean isWeekend(Day d) {
        return d == Day.SATURDAY || d == Day.SUNDAY;    // enums CAN be compared with ==
    }

    static TrafficLight next(TrafficLight light) {
        return switch (light) {                          // the compiler checks you covered every value
            case RED -> TrafficLight.GREEN;
            case GREEN -> TrafficLight.AMBER;
            case AMBER -> TrafficLight.RED;
        };
    }

    public static void main(String[] args) {
        Day today = Day.FRIDAY;
        System.out.println(today + " is a weekend? " + isWeekend(today));

        for (Day d : Day.values()) {                     // every value, in order
            System.out.println(d.ordinal() + " " + d + (isWeekend(d) ? " (weekend)" : ""));
        }

        Day fromText = Day.valueOf("SUNDAY");            // text to enum (exact capitals!)
        System.out.println(fromText);

        TrafficLight light = TrafficLight.RED;
        for (int i = 0; i < 4; i++) {
            System.out.print(light + " -> ");
            light = next(light);
        }
        System.out.println(light);

        // Day typo = Day.FRYDAY;       // error: no such value. Strings can't protect you like this.
    }
}
