// Example 11: the Towers of Hanoi. Move n discs from peg A to peg C, one at a
// time, never putting a bigger disc on a smaller one. Almost impossible to
// solve with loops; three lines with recursion.
// Run it with:  java Ex11TowersOfHanoi.java

public class Ex11TowersOfHanoi {

    static int moves = 0;

    static void hanoi(int n, char from, char to, char spare) {
        if (n == 0) {
            return;
        }
        hanoi(n - 1, from, spare, to);       // move the n-1 discs above out of the way
        moves++;
        System.out.println("Move disc " + n + " from " + from + " to " + to);
        hanoi(n - 1, spare, to, from);       // and put them back on top
    }

    public static void main(String[] args) {
        hanoi(4, 'A', 'C', 'B');
        System.out.println(moves + " moves. (n discs always take 2^n - 1 moves:");
        System.out.println("64 discs would take 584 billion years at one move per second.)");
    }
}
