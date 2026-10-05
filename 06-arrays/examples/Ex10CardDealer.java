// Example 10: build a deck of 52 cards, shuffle it, and deal two hands.
// Run it with:  java Ex10CardDealer.java

import java.util.Random;

public class Ex10CardDealer {
    public static void main(String[] args) {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};

        // Build the deck: one card for every suit and rank
        String[] deck = new String[suits.length * ranks.length];
        int next = 0;
        for (String suit : suits) {
            for (String rank : ranks) {
                deck[next] = rank + " of " + suit;
                next++;
            }
        }
        System.out.println("Deck has " + deck.length + " cards. Top card: " + deck[0]);

        // Shuffle (the Fisher-Yates shuffle): swap each card with a random
        // card at or before it, working from the end of the deck.
        Random random = new Random();
        for (int i = deck.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = deck[i];
            deck[i] = deck[j];
            deck[j] = temp;
        }

        // Deal five cards each, alternately
        System.out.println("Player 1:          Player 2:");
        for (int c = 0; c < 5; c++) {
            System.out.printf("%-18s %s%n", deck[2 * c], deck[2 * c + 1]);
        }
    }
}
