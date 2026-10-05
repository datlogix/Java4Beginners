// Module 16 Project, Track C: DNA Toolkit
// Author: YOUR NAME

/** Recursive tools for DNA sequences written with the letters A, C, G and T. */
public class Dna {

    /** The complementary base: A <-> T, C <-> G. */
    public static char complement(char base) {
        return switch (base) {
            case 'A' -> 'T';
            case 'T' -> 'A';
            case 'C' -> 'G';
            case 'G' -> 'C';
            default -> throw new IllegalArgumentException("Not a DNA base: " + base);
        };
    }

    /** The reverse complement, RECURSIVELY: reverseComplement("GAATTC") is "GAATTC";
     *  reverseComplement("ACG") is "CGT". */
    public static String reverseComplement(String dna) {
        return "";   // TODO
    }

    /** How many G and C bases there are, RECURSIVELY. */
    public static int gcCount(String dna) {
        return 0;   // TODO
    }

    /** True if dna equals its own reverse complement (like restriction sites
     *  GAATTC and GGATCC), checked RECURSIVELY from both ends inwards. */
    public static boolean isReversePalindrome(String dna) {
        return false;   // TODO
    }
}
