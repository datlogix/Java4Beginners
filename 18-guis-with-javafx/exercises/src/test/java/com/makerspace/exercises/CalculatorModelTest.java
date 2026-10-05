package com.makerspace.exercises;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** The specification for CalculatorModel. Don't change these tests: make them pass! */
class CalculatorModelTest {

    /** Presses each key in turn (keys separated by spaces) and returns the display. */
    private static String press(String keys) {
        CalculatorModel m = new CalculatorModel();
        for (String key : keys.split(" ")) {
            m.press(key);
        }
        return m.display();
    }

    @ParameterizedTest(name = "{0}  ->  {1}")
    @CsvSource({
        "C, 0",
        "1 2, 12",
        "0 7, 7",
        "1 2 + 3 =, 15",
        "1 2 + 3, 3",
        "2 + 3 x 4 =, 20",
        "2 + 3 +, 5",
        "9 - 1 2 =, -3",
        "0 . 5 + 0 . 2 5 =, 0.75",
        "1 . . 5, 1.5",
        "1 / 3 =, 0.3333333333",
        "7 / 0 =, Error",
        "7 / 0 = 5, Error",
        "7 / 0 = C 5, 5",
        "4 x 2 . 5 =, 10",
        "8 =, 8",
    })
    void keySequences(String keys, String expected) {
        assertEquals(expected, press(keys));
    }
}
