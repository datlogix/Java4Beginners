// The package line says which package this class belongs to. It must match the
// folders: com/makerspace/util/TextTools.java
package com.makerspace.util;

/** Text helpers that any program can import. */
public class TextTools {

    /** "kofi annan" -> "Kofi Annan" */
    public static String titleCase(String text) {
        StringBuilder result = new StringBuilder();
        for (String word : text.trim().split(" +")) {
            if (!result.isEmpty()) {
                result.append(' ');
            }
            result.append(Character.toUpperCase(word.charAt(0))).append(word.substring(1).toLowerCase());
        }
        return result.toString();
    }

    /** A line of text centred in a box of the given width. */
    public static String boxed(String text, int width) {
        int left = (width - text.length()) / 2;
        String inside = " ".repeat(Math.max(0, left)) + text;
        return "+" + "-".repeat(width) + "+\n|" + String.format("%-" + width + "s", inside) + "|\n+" + "-".repeat(width) + "+";
    }

    // No "public": package-private. Only classes in com.makerspace.util can use it.
    static boolean isBlank(String text) {
        return text == null || text.isBlank();
    }
}
