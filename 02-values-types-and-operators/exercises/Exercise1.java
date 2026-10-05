// Exercise 1: Let Java do the sums.
//
// Every number you print must be CALCULATED by Java from the values given.
// Never work an answer out yourself and type it in.
//
// When it's finished, running it prints EXACTLY:
//
//     A 7385-second video lasts 2 h 3 min 5 s
//     Splitting 237 cedis between 4 friends: 59 cedis each, 1 cedi left over
//     Average of 31, 29 and 34 degrees: 31.333333333333332
//     Rounded to 1 decimal place: 31.3
//     A 12 V battery across 470 ohms gives 25.53 mA
//     Is 2026 divisible by 4? false
//
// Hints:
//   - Hours: seconds / 3600. Minutes: (seconds % 3600) / 60. Seconds: seconds % 60.
//   - Average: if every number is an int, the answer is an int. Make one a double.
//   - Rounding to 1 place: Math.round(x * 10) / 10.0
//   - Current in mA = voltage / resistance * 1000, rounded to 2 decimal places.
//   - "Divisible by 4" means the remainder after dividing by 4 is 0.
//
// Run it with:  java Exercise1.java

public class Exercise1 {
    public static void main(String[] args) {
        // Line 1: the video length (7385 seconds) in hours, minutes and seconds
        System.out.println("A 7385-second video lasts " + (7385 / 3600) + " h "
                + "TODO" + " min " + "TODO" + " s");

        // Line 2: 237 cedis shared between 4 friends
        // TODO

        // Line 3: the average of 31, 29 and 34
        // TODO

        // Line 4: that average rounded to 1 decimal place
        // TODO

        // Line 5: the current in milliamps, rounded to 2 decimal places
        // TODO

        // Line 6: true or false: is 2026 divisible by 4? (use % and ==)
        // TODO
    }
}
