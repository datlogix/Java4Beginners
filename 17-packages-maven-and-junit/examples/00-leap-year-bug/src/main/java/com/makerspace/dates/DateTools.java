package com.makerspace.dates;

/** Small calendar calculations. One of them has a bug. The tests will find it. */
public class DateTools {

    /** True if year is a leap year: divisible by 4, except century years,
     *  which must also be divisible by 400. */
    public static boolean isLeapYear(int year) {
        return year % 4 == 0 && year % 100 != 0;
    }

    /** The number of days in a month (1-12) of a given year. */
    public static int daysInMonth(int month, int year) {
        if (month < 1 || month > 12) {
            throw new IllegalArgumentException("No such month: " + month);
        }
        return switch (month) {
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> 31;
        };
    }

    /** Which day of the year a date is: 1 January is day 1. */
    public static int dayOfYear(int day, int month, int year) {
        int total = day;
        for (int m = 1; m < month; m++) {
            total += daysInMonth(m, year);
        }
        return total;
    }
}
