// Example 10: random numbers with java.util.Random.
// Run it with:  java Ex10Random.java  (run it several times)

import java.util.Random;

public class Ex10Random {
    public static void main(String[] args) {
        Random random = new Random();

        // nextInt(n) gives 0, 1, ..., n-1. So for a dice (1 to 6), add 1.
        int dice = random.nextInt(6) + 1;
        System.out.println("Dice: " + dice);

        // Any range from low to high:  random.nextInt(high - low + 1) + low
        int temperature = random.nextInt(35 - 22 + 1) + 22;     // 22 to 35
        System.out.println("Random temperature: " + temperature + " C");

        // A coin: nextBoolean() is true or false, 50/50
        System.out.println(random.nextBoolean() ? "Heads" : "Tails");

        // nextDouble() gives a decimal from 0.0 up to (not including) 1.0
        double chance = random.nextDouble();
        System.out.printf("Chance of rain: %.0f%%%n", chance * 100);

        // Ten dice rolls
        System.out.print("Ten rolls:");
        for (int i = 0; i < 10; i++) {
            System.out.print(" " + (random.nextInt(6) + 1));
        }
        System.out.println();

        // Math.random() also works: a double from 0.0 to just under 1.0
        int alsoDice = (int) (Math.random() * 6) + 1;
        System.out.println("Math.random dice: " + alsoDice);
    }
}
