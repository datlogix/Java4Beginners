// Exercise 2: Bug hunt.
//
// This program has FOUR mistakes, one on each line inside main. When it's
// fixed, running it prints:
//
//     Bug hunt complete!
//     Java is case-sensitive.
//     Quotes must match.
//     10 / 2 = 5
//
// How to work:
//   1. Run the file. Read the FIRST error message: the file name, the line
//      number, and what the compiler expected.
//   2. Fix ONLY the problem that message points at.
//   3. Run again. Repeat until it works.
//
// One mistake can cause several error messages (a missing quote can confuse
// the compiler about everything after it), so always fix the FIRST message
// and recompile, rather than trying to fix every message at once.
//
// When you're done:
//   - Add a comment on each line you fixed, saying what was wrong and what
//     the message said (e.g. "';' expected", "cannot find symbol").
//   - Answer this in a comment at the bottom of the file: three of the bugs
//     were reported by the COMPILER, and one only appeared when the program
//     RAN. Which one, and why couldn't the compiler spot it? (Hint: the
//     module README explains compile-time versus runtime errors.)

public class Exercise2 {
    public static void main(String[] args) {
        System.out.println("Bug hunt complete!")
        system.out.println("Java is case-sensitive.");
        System.out.println("Quotes must match.');
        System.out.println("10 / 2 = " + (10 / 0));
    }
}
