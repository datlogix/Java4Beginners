// Example 0: the hook. Type your date of birth; Java works out the day of the
// week you were born, your Akan day name, and a few other things.
// Run it with:  java Ex00BirthdayDetective.java

import java.time.LocalDate;
import java.util.Scanner;

public class Ex00BirthdayDetective {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Day you were born (1-31): ");
        int day = Integer.parseInt(in.nextLine().trim());
        System.out.print("Month (1-12): ");
        int month = Integer.parseInt(in.nextLine().trim());
        System.out.print("Year: ");
        int year = Integer.parseInt(in.nextLine().trim());

        LocalDate birthday = LocalDate.of(year, month, day);
        String weekday = birthday.getDayOfWeek().toString();     // e.g. "FRIDAY"

        System.out.println();
        System.out.println("You were born on a " + weekday + ".");

        String akanNames = switch (weekday) {
            case "SUNDAY" -> "Kwesi or Akosua";
            case "MONDAY" -> "Kwadwo or Adwoa";
            case "TUESDAY" -> "Kwabena or Abena";
            case "WEDNESDAY" -> "Kwaku or Akua";
            case "THURSDAY" -> "Yaw or Yaa";
            case "FRIDAY" -> "Kofi or Afua";
            default -> "Kwame or Ama";
        };
        System.out.println("Your Akan day name would be " + akanNames + ".");

        if (weekday.equals("SATURDAY") || weekday.equals("SUNDAY")) {
            System.out.println("A weekend baby: your parents didn't have to take a day off work!");
        } else {
            System.out.println("A weekday baby: you arrived during working hours.");
        }

        boolean leapYear = (year % 4 == 0 && year % 100 != 0) || year % 400 == 0;
        if (leapYear) {
            System.out.println(year + " was a leap year, with 366 days.");
        } else {
            System.out.println(year + " was not a leap year.");
        }

        if (month == 2 && day == 29) {
            System.out.println("You were born on February 29th: you only get a real birthday every four years!");
        }
    }
}
