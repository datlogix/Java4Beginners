// Example 0: the hook. Adopt two virtual pets, and look after them.
// Each pet remembers its OWN hunger and happiness.
// Run it with:  java Ex00VirtualPet.java

import java.util.Scanner;

public class Ex00VirtualPet {

    // A class describes a new TYPE: what every Pet knows, and what it can do.
    static class Pet {
        String name;
        int hunger = 3;        // 0 = full, 10 = starving
        int happiness = 7;     // 0 = miserable, 10 = delighted

        Pet(String name) {
            this.name = name;
        }

        void feed() {
            hunger = Math.max(0, hunger - 3);
            System.out.println(name + " munches happily. Yum!");
        }

        void play() {
            happiness = Math.min(10, happiness + 3);
            hunger = Math.min(10, hunger + 1);
            System.out.println(name + " chases a ball around the yard!");
        }

        void timePasses() {
            hunger = Math.min(10, hunger + 1);
            happiness = Math.max(0, happiness - 1);
        }

        String mood() {
            if (hunger >= 8) {
                return "STARVING";
            } else if (happiness <= 2) {
                return "sulking";
            } else if (happiness >= 8 && hunger <= 3) {
                return "overjoyed";
            }
            return "content";
        }

        String bar(int value) {
            return "[" + "#".repeat(value) + " ".repeat(10 - value) + "]";
        }

        void showStatus() {
            System.out.printf("%-8s hunger %s  happiness %s  %s%n", name, bar(hunger), bar(happiness), mood());
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Name your first pet: ");
        Pet first = new Pet(in.nextLine().trim());
        System.out.print("Name your second pet: ");
        Pet second = new Pet(in.nextLine().trim());

        while (true) {
            System.out.println();
            first.showStatus();
            second.showStatus();
            System.out.print("\nWho do you want to look after? (1/2, or q to quit) ");
            String who = in.nextLine().trim();
            if (who.equalsIgnoreCase("q")) {
                break;
            }
            Pet pet = who.equals("1") ? first : second;
            System.out.print("What will you do with " + pet.name + "? (f)eed or (p)lay: ");
            String action = in.nextLine().trim().toLowerCase();
            if (action.equals("f")) {
                pet.feed();
            } else if (action.equals("p")) {
                pet.play();
            }
            first.timePasses();
            second.timePasses();
        }
        System.out.println("Goodbye, " + first.name + " and " + second.name + "!");
    }
}
