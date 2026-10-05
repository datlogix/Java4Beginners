// Exercise 1: A notes app that remembers.
//
// Keep notes in a file called notes.txt, one note per line, each starting with
// the date it was added. The notes must still be there the next time the
// program runs.
//
// Sample run:
//
//     Commands: add <text>, list, search <word>, delete <number>, clear, quit
//     > add Buy a 10k resistor
//     Saved.
//     > add Email Dr Mensah about the lab report
//     Saved.
//     > list
//     1. [2026-10-04] Buy a 10k resistor
//     2. [2026-10-04] Email Dr Mensah about the lab report
//     > search RESISTOR
//     1. [2026-10-04] Buy a 10k resistor
//     > delete 1
//     Deleted: [2026-10-04] Buy a 10k resistor
//     > delete 7
//     There's no note 7.
//     > quit
//
// Rules:
//   1. "add" APPENDS one line to the file (StandardOpenOption.CREATE and APPEND).
//      The date comes from LocalDate.now().
//   2. "list" and "search" read the file each time. If the file doesn't exist
//      yet, say "No notes yet." (don't crash!)
//   3. "search" ignores capitals.
//   4. "delete" reads all the lines, removes one, and writes the rest back.
//   5. "clear" asks "Are you sure? (y/n)" before deleting the file.
//   6. Every file operation is inside a try/catch for IOException, with a
//      friendly message. Bad numbers for delete don't crash either.
//
// Run it with:  java Exercise1.java

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Exercise1 {
    static final Path NOTES = Path.of("notes.txt");

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Commands: add <text>, list, search <word>, delete <number>, clear, quit");
        while (true) {
            System.out.print("> ");
            String line = in.nextLine().trim();
            String command = line;
            String rest = "";
            int space = line.indexOf(' ');
            if (space != -1) {
                command = line.substring(0, space);
                rest = line.substring(space + 1).trim();
            }
            command = command.toLowerCase();
            if (command.equals("quit")) {
                break;
            }
            // TODO: add, list, search, delete, clear (one method each is neater!)
        }
    }

    /** Reads every note, or returns an empty list if there's no file yet. */
    static List<String> readNotes() throws IOException {
        // TODO
        return new ArrayList<>();
    }
}
