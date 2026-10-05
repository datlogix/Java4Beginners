package com.makerspace.dates;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/** Automated tests. Run them all with:  ./mvnw test   (Windows: mvnw test) */
class DateToolsTest {

    @Test
    void ordinaryLeapYear() {
        assertTrue(DateTools.isLeapYear(2024));
    }

    @Test
    void ordinaryNonLeapYear() {
        assertFalse(DateTools.isLeapYear(2026));
    }

    @Test
    void centuryYearIsNotLeap() {
        assertFalse(DateTools.isLeapYear(1900));
    }

    @Test
    void yearDivisibleBy400IsLeap() {
        assertTrue(DateTools.isLeapYear(2000), "2000 divides by 400, so it IS a leap year");
    }

    @Test
    void februaryInALeapYear() {
        assertEquals(29, DateTools.daysInMonth(2, 2024));
    }

    @Test
    void lastDayOfALeapYear() {
        assertEquals(366, DateTools.dayOfYear(31, 12, 2000));
    }

    @Test
    void monthThirteenIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> DateTools.daysInMonth(13, 2026));
    }
}
