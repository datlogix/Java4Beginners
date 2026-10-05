// Example 7: the classic switch statement. Note the break at the end of each case.
// Run it with:  java Ex07Switch.java

import java.util.Scanner;

public class Ex07Switch {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Traffic light colour (red/amber/green)? ");
        String colour = in.nextLine().trim().toLowerCase();

        switch (colour) {
            case "red":
                System.out.println("Stop.");
                break;
            case "amber":
                System.out.println("Get ready to stop.");
                break;
            case "green":
                System.out.println("Go, if the way is clear.");
                break;
            default:
                System.out.println("That's not a traffic light colour.");
        }

        // Without break, Java "falls through" into the next case. Sometimes that's useful:
        System.out.print("Month number (1-12)? ");
        int month = Integer.parseInt(in.nextLine().trim());
        switch (month) {
            case 12:
            case 1:
            case 2:
                System.out.println("Harmattan season.");
                break;
            case 4:
            case 5:
            case 6:
            case 7:
                System.out.println("Major rainy season (in the south).");
                break;
            default:
                System.out.println("Some other part of the year.");
        }
    }
}
