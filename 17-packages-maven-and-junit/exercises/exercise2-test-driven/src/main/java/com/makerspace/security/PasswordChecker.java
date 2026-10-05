package com.makerspace.security;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 2: test-driven development.
 *
 * The tests in src/test/java/com/makerspace/security/PasswordCheckerTest.java are
 * already written: they ARE the specification. Read them first. Then make them
 * pass, ONE AT A TIME: run ./mvnw test, pick a failing test, write just enough
 * code to make it pass, run the tests again. Repeat until everything is green.
 * Then tidy your code (and run the tests once more to prove you didn't break it).
 */
public class PasswordChecker {

    /** Every rule the password breaks, as a message. An empty list means it passes them all. */
    public static List<String> problems(String password) {
        List<String> found = new ArrayList<>();
        // TODO
        return found;
    }

    public static Strength strength(String password) {
        return Strength.WEAK;   // TODO
    }

    public static boolean isAcceptable(String password) {
        return false;   // TODO
    }
}
