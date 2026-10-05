// Exercise 2: Process a CSV file of marks into a report.
//
// data/marks.csv holds each student's continuous assessment (CA, out of 40)
// and exam mark (out of 60). Some lines are broken. Read the file, skip and
// report the broken lines, work out each valid student's total and grade, write
// the results to data/report.csv, and print a summary.
//
// A line is broken if it doesn't have 4 fields, a mark isn't a whole number,
// the CA isn't 0-40, or the exam isn't 0-60.
//
// Grades from the total: A 80+, B 70+, C 60+, D 50+, otherwise F.
// (Your Module 12 Grade enum would be perfect here, if you want to reuse it.)
//
// Expected output:
//
//     Skipped line 5: ca 'abc' isn't a whole number
//     Skipped line 7: expected 4 fields, found 3
//     Skipped line 8: ca 41 is outside 0-40
//     Processed 6 students. Report written to data/report.csv
//     Class average: 74.2
//     Top student: Efua Sackey (95)
//     Grades: A=3 B=1 C=1 D=0 F=1
//
// And data/report.csv should contain:
//
//     id,name,total,grade
//     S01,Akosua Badu,83,A
//     S02,Kwame Ofori,67,C
//     S03,Efua Sackey,95,A
//     S05,Adwoa Mensah,41,F
//     S08,Nii Lamptey,74,B
//     S09,Abena Boateng,85,A
//
// Run it from the exercises folder with:  java Exercise2.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Exercise2 {
    static final Path INPUT = Path.of("data", "marks.csv");
    static final Path OUTPUT = Path.of("data", "report.csv");

    record Result(String id, String name, int total, String grade) {
        String toCsv() {
            return id + "," + name + "," + total + "," + grade;
        }
    }

    public static void main(String[] args) {
        // TODO: read the lines (handle IOException), skip the header,
        //       turn each good line into a Result, and report the bad ones
        // TODO: write the report file, with a header line
        // TODO: print the summary
    }

    /** Turns one CSV line into a Result, or throws IllegalArgumentException
     *  with a message saying what's wrong with it. */
    static Result parse(String line) {
        // TODO
        throw new IllegalArgumentException("not written yet");
    }

    static String grade(int total) {
        return "F";   // TODO
    }
}
