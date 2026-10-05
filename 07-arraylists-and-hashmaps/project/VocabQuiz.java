// Module 7 Project: Vocabulary Quiz Master
// Author: YOUR NAME
//
// Follow the TODOs. Run the program after each one.
// Run it with:  java VocabQuiz.java

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VocabQuiz {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // The vocabulary: word -> meaning. Replace these with words YOU want
        // to learn: another language, a subject's key terms, anything.
        Map<String, String> vocab = new HashMap<>();
        vocab.put("akwaaba", "welcome");
        vocab.put("medaase", "thank you");
        vocab.put("aduane", "food");
        vocab.put("nsuo", "water");
        vocab.put("sukuu", "school");
        vocab.put("fie", "home");
        // TODO: add at least four more words

        ArrayList<String> wrong = new ArrayList<>();   // words answered wrongly
        String choice;

        do {
            System.out.println();
            System.out.println("=== VOCABULARY QUIZ MASTER: " + vocab.size() + " words ===");
            System.out.println("1) Take the quiz");
            System.out.println("2) Practise the words I got wrong");
            System.out.println("3) Add a word");
            System.out.println("4) Show all words");
            System.out.println("5) Quit");
            System.out.print("Choose: ");
            choice = in.nextLine().trim();

            switch (choice) {
                case "1" -> {
                    // TODO: put the words (the map's keys) in an ArrayList,
                    //       shuffle it, and ask the meaning of each one.
                    //       Accept answers in any capitals. Keep a score, and add
                    //       each word answered wrongly to the wrong list (once only!).
                    //       At the end, print the score and a percentage.
                }
                case "2" -> {
                    // TODO: quiz only the words in the wrong list. Remove a word
                    //       from the list when it's answered correctly.
                    //       If the list is empty, say so.
                }
                case "3" -> {
                    // TODO: ask for a word and its meaning, and add it.
                    //       If the word is already there, show its current meaning
                    //       and ask whether to replace it.
                }
                case "4" -> {
                    // TODO: print every word and meaning in ALPHABETICAL order
                    //       (hint: copy the map into a TreeMap)
                }
                case "5" -> System.out.println("Keep practising!");
                default -> System.out.println("Please choose 1-5.");
            }
        } while (!choice.equals("5"));
    }
}
