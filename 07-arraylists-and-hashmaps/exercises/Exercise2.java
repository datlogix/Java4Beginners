// Exercise 2: Class election vote counter.
//
// Each line typed in is a vote: a voter ID and a candidate's name, separated
// by a space. Typing end finishes the election. Count the votes, making sure
// nobody votes twice, and announce the result.
//
// Sample run:
//
//     Enter votes as: <voter ID> <candidate>. Type end to finish.
//     > S001 Akua
//     > S002 kwame
//     > S003 Akua
//     > S001 Kwame
//       S001 has already voted. Vote rejected.
//     > S004 Efua
//     > S005 KWAME
//     > S006 akua
//     > end
//
//     RESULTS (6 votes)
//     Akua    3  50.0%  ***
//     Efua    1  16.7%  *
//     Kwame   2  33.3%  **
//     Winner: Akua
//
// Rules:
//   1. Use a HashSet<String> of voter IDs to reject a second vote from the
//      same voter.
//   2. Use a map from candidate to number of votes. Store names with a capital
//      first letter and the rest lower case, so "kwame", "Kwame" and "KWAME"
//      all count for Kwame.
//   3. Print the results in ALPHABETICAL order (a TreeMap does this for you),
//      with the percentage of the total to 1 decimal place and one * per vote.
//   4. If two or more candidates share the top score, print
//      "It's a tie between Akua and Kwame!" (listing every tied candidate)
//      instead of a winner.
//   5. If no valid votes were cast, print "No votes were cast."
//
// Run it with:  java Exercise2.java

import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

public class Exercise2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Set<String> voted = new HashSet<>();
        Map<String, Integer> tally = new TreeMap<>();

        System.out.println("Enter votes as: <voter ID> <candidate>. Type end to finish.");
        // TODO: the voting loop

        // TODO: the results, the winner or the tie
    }
}
