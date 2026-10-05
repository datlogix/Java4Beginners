// Exercise 1: Shopping list manager.
//
// Keep a shopping list in an ArrayList<String>, and let the user manage it
// with commands until they type quit.
//
// Sample run:
//
//     Commands: add <item>, remove <item>, remove <number>, list, sort, clear, quit
//     > add tomatoes
//     Added tomatoes.
//     > add Onions
//     Added Onions.
//     > add onions
//     onions is already on the list.
//     > add rice
//     Added rice.
//     > list
//     1. tomatoes
//     2. Onions
//     3. rice
//     > remove 1
//     Removed tomatoes.
//     > remove beans
//     beans isn't on the list.
//     > sort
//     > list
//     1. Onions
//     2. rice
//     > quit
//     You still need to buy 2 item(s).
//
// Rules:
//   1. Split each command into a word and the rest: the command is the text
//      before the first space; the item is everything after it (trimmed).
//   2. "add" refuses duplicates, IGNORING capitals (Onions and onions are the
//      same item). Hint: loop over the list and use equalsIgnoreCase, because
//      contains() cares about capitals.
//   3. "remove" works with a NUMBER (as shown by list) or a NAME (any capitals).
//      Hint: if every character of the item is a digit, treat it as a number.
//      Character.isDigit(c) tells you if a char is a digit.
//   4. "list" on an empty list prints "The list is empty."
//   5. "sort" sorts alphabetically, ignoring capitals:
//      list.sort(String.CASE_INSENSITIVE_ORDER);
//
// Run it with:  java Exercise1.java

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();

        System.out.println("Commands: add <item>, remove <item>, remove <number>, list, sort, clear, quit");
        while (true) {
            System.out.print("> ");
            String line = in.nextLine().trim();
            String command = line;
            String item = "";
            int space = line.indexOf(" ");
            if (space != -1) {
                command = line.substring(0, space).toLowerCase();
                item = line.substring(space + 1).trim();
            }

            if (command.equals("quit")) {
                break;
            }
            // TODO: add, remove, list, sort, clear, and an "Unknown command." message
        }
        // TODO: the goodbye message with the number of items
    }
}
