package com.makerspace.dates;

public class DateApp {
    public static void main(String[] args) {
        for (int year : new int[]{1900, 2000, 2024, 2026}) {
            System.out.println(year + (DateTools.isLeapYear(year) ? " is" : " is not") + " a leap year");
        }
        System.out.println("31 December 2000 is day " + DateTools.dayOfYear(31, 12, 2000) + " of the year");
    }
}
