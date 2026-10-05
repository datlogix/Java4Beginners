// Run it with:
//     javac -d out *.java
//     java -cp out NetworkApp

public class NetworkApp {
    public static void main(String[] args) {
        // TODO: write the Parallel class (R = 1 / (1/R1 + 1/R2 + ...)), then build:
        //     100 + (220 || (100 + 230)) + 47
        // and check its resistance by hand.
        Network n = new Series(new Resistor(100), new Resistor(47));
        System.out.println(n.describe() + " = " + n.resistance() + " ohms");

        // TODO: the E12 list, nearest values, the two-resistor search and the timing
        //       experiment described in the README
    }
}
