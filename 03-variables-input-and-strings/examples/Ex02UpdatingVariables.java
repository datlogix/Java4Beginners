// Example 2: changing what's in a variable.
// Run it with:  java Ex02UpdatingVariables.java

public class Ex02UpdatingVariables {
    public static void main(String[] args) {
        int score = 10;
        System.out.println("Start: " + score);

        score = 25;                 // replace the old value completely
        System.out.println("Replaced: " + score);

        score = score + 5;          // read the old value, add 5, store the result
        System.out.println("Plus 5: " + score);

        score += 10;                // shorthand for score = score + 10
        score -= 3;                 // score = score - 3
        score *= 2;                 // score = score * 2
        System.out.println("After += 10, -= 3, *= 2: " + score);

        score++;                    // add exactly 1 (you'll use this constantly in loops)
        System.out.println("After ++: " + score);
        score--;                    // subtract exactly 1
        System.out.println("After --: " + score);

        // Swapping two variables needs a third, temporary box.
        String left = "tea", right = "coffee";
        String temp = left;
        left = right;
        right = temp;
        System.out.println("Swapped: left = " + left + ", right = " + right);
    }
}
