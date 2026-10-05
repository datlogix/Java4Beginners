// Module 17 Project, Track A: texttools
// Author: YOUR NAME
package com.makerspace.texttools;

import java.util.List;
import java.util.Map;

/** Text-analysis helpers. Every method is static, and none of them prints anything. */
public final class TextTools {

    private TextTools() {
        // a "utility class": all static methods, so nobody needs to create one
    }

    /** The words in text, lower case, with punctuation removed. "Hello, World!" -> [hello, world] */
    public static List<String> words(String text) {
        String cleaned = text.toLowerCase().replaceAll("[^a-z0-9' ]", " ").trim();
        return cleaned.isEmpty() ? List.of() : List.of(cleaned.split(" +"));
    }

    /** How many times each word appears, in alphabetical order. */
    public static Map<String, Integer> wordFrequencies(String text) {
        return Map.of();   // TODO
    }

    /** Minutes to read the text at 200 words per minute, rounded UP; 0 for no words. */
    public static int readingMinutes(String text) {
        return 0;   // TODO
    }

    /** A web-address-friendly version: "Hello, World! 2026" -> "hello-world-2026" */
    public static String slugify(String text) {
        return "";   // TODO
    }

    // TODO: at least three more methods of your own (see the README for ideas)
}
