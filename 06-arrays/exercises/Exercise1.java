// Exercise 1: Class results report.
//
// The names and marks of a class are already stored in two parallel arrays
// below. Write the code that prints this report (it must still work if the
// arrays are changed to hold different students):
//
//     RESULTS: 8 students
//     Abena       67  ######
//     Kofi        82  ########
//     Yaw         45  ####
//     Esi         91  #########
//     Kwame       58  #####
//     Akosua      73  #######
//     Kojo        39  ###
//     Efua        88  ########
//
//     Average:      67.9
//     Top student:  Esi (91)
//     Passed (50+): 6 of 8
//     Sorted marks: [39, 45, 58, 67, 73, 82, 88, 91]
//     Median:       70.0
//
// Rules:
//   1. One # for every 10 marks (67 -> 6 #s).
//   2. The average has 1 decimal place.
//   3. Sort a COPY of the marks for the sorted line and the median, so the
//      names and marks still line up. Print Arrays.toString(marks) at the very
//      end to prove the original order hasn't changed.
//   4. The median is the middle value of the sorted marks. With an EVEN number
//      of marks, it's the average of the two middle values (67 and 73 -> 70.0).
//      Make it work for an odd number of students too: test it by deleting
//      one student from both arrays.
//
// Run it with:  java Exercise1.java

import java.util.Arrays;

public class Exercise1 {
    public static void main(String[] args) {
        String[] names = {"Abena", "Kofi", "Yaw", "Esi", "Kwame", "Akosua", "Kojo", "Efua"};
        int[] marks = {67, 82, 45, 91, 58, 73, 39, 88};

        System.out.println("RESULTS: " + names.length + " students");
        // TODO: one line per student, with the bar

        // TODO: the average, the top student, and the pass count

        // TODO: the sorted copy and the median

        // TODO: print the original marks array, to show it's unchanged
    }
}
