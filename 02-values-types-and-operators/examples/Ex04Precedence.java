// Example 4: operator precedence. *, / and % happen before + and -.
// Brackets always win. When in doubt, add brackets.
// Run it with:  java Ex04Precedence.java

public class Ex04Precedence {
    public static void main(String[] args) {
        System.out.println(2 + 3 * 4);          // 14, not 20
        System.out.println((2 + 3) * 4);        // 20
        System.out.println(10 - 4 - 3);         // 3: same level, so left to right
        System.out.println(20 / 2 * 5);         // 50, not 2: left to right
        System.out.println(20 / (2 * 5));       // 2

        // The average of three marks. The brackets matter!
        System.out.println((70 + 80 + 90) / 3.0);   // 80.0
        System.out.println(70 + 80 + 90 / 3.0);     // 180.0, oops
    }
}
