// Example 3: recursion on Strings and arrays: solve the problem for the first
// item, and let recursion handle "the rest".
// Run it with:  java Ex03RecursionOnStringsAndArrays.java

public class Ex03RecursionOnStringsAndArrays {

    static String reverse(String s) {
        if (s.length() <= 1) {
            return s;
        }
        return reverse(s.substring(1)) + s.charAt(0);       // reverse the rest, then add the first letter
    }

    static boolean isPalindrome(String s) {
        if (s.length() <= 1) {
            return true;
        }
        if (s.charAt(0) != s.charAt(s.length() - 1)) {
            return false;
        }
        return isPalindrome(s.substring(1, s.length() - 1));   // check the middle
    }

    /** The sum of a[from], a[from + 1], ... to the end. */
    static int sum(int[] a, int from) {
        if (from == a.length) {
            return 0;                                         // nothing left
        }
        return a[from] + sum(a, from + 1);
    }

    /** How many times c appears in s. */
    static int count(String s, char c) {
        if (s.isEmpty()) {
            return 0;
        }
        return (s.charAt(0) == c ? 1 : 0) + count(s.substring(1), c);
    }

    /** Every way to order the letters of s. */
    static void permutations(String done, String left) {
        if (left.isEmpty()) {
            System.out.print(done + " ");
            return;
        }
        for (int i = 0; i < left.length(); i++) {
            permutations(done + left.charAt(i), left.substring(0, i) + left.substring(i + 1));
        }
    }

    public static void main(String[] args) {
        System.out.println(reverse("recursion"));
        System.out.println(isPalindrome("racecar") + " " + isPalindrome("robot"));
        System.out.println(sum(new int[]{3, 1, 4, 1, 5, 9}, 0));
        System.out.println(count("mississippi", 's'));
        permutations("", "ABC");
        System.out.println();
    }
}
