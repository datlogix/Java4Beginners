package com.makerspace.text;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * Exercise 1: find the bugs with tests.
 *
 * Three tests are written for you, and they pass. Write AT LEAST TWELVE more:
 * at least two for every method in TextStats. Think about:
 *   - ordinary cases, and edge cases: empty text, one word, extra spaces,
 *     capitals, ties
 *   - exactly what each method's Javadoc comment promises
 *
 * Run them with:  ./mvnw test   (Windows: mvnw test)
 *
 * When FOUR of your tests fail, you've found the four bugs. Write a comment above
 * each failing test saying what the bug is. Then fix TextStats so every test passes.
 */
class TextStatsTest {

    @Test
    void countsWordsInASentence() {
        assertEquals(4, TextStats.wordCount("the quick brown fox"));
    }

    @Test
    void capitalisesEachWord() {
        assertEquals("Hello World", TextStats.capitalise("hello WORLD"));
    }

    @Test
    void listenAndSilentAreAnagrams() {
        assertTrue(TextStats.isAnagram("Listen", "Silent"));
    }

    // TODO: at least twelve more tests
}
