package com.makerspace.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/** The specification for PasswordChecker. Don't change these tests: make them pass! */
class PasswordCheckerTest {

    @Test
    void aGoodPasswordHasNoProblems() {
        assertEquals(List.of(), PasswordChecker.problems("Akwaaba!2026"));
    }

    @Test
    void tooShort() {
        assertTrue(PasswordChecker.problems("Ab1!").contains("must be at least 8 characters"));
    }

    @Test
    void exactlyEightCharactersIsLongEnough() {
        assertFalse(PasswordChecker.problems("Abcdef1!").contains("must be at least 8 characters"));
    }

    @Test
    void needsADigit() {
        assertTrue(PasswordChecker.problems("Akwaaba!!").contains("must contain a digit"));
    }

    @Test
    void needsAnUpperCaseLetter() {
        assertTrue(PasswordChecker.problems("akwaaba!1").contains("must contain an upper case letter"));
    }

    @Test
    void needsALowerCaseLetter() {
        assertTrue(PasswordChecker.problems("AKWAABA!1").contains("must contain a lower case letter"));
    }

    @Test
    void needsASymbol() {
        assertTrue(PasswordChecker.problems("Akwaaba12").contains("must contain a symbol (not a letter, digit or space)"));
    }

    @Test
    void mustNotContainSpaces() {
        assertTrue(PasswordChecker.problems("Akwaaba 1!").contains("must not contain spaces"));
    }

    @Test
    void mustNotContainTheWordPassword() {
        assertTrue(PasswordChecker.problems("MyPassWord1!").contains("must not contain the word 'password'"));
    }

    @Test
    void reportsEveryProblemInOrder() {
        assertEquals(List.of(
                "must be at least 8 characters",
                "must contain a digit",
                "must contain an upper case letter",
                "must contain a symbol (not a letter, digit or space)"), PasswordChecker.problems("abc"));
    }

    @ParameterizedTest(name = "{0} is {1}")
    @CsvSource({
        "abc, WEAK",                 // breaks several rules
        "Abcdef1!, MEDIUM",          // passes every rule, but only 8-11 characters long
        "Akwaaba!2026, STRONG",      // passes every rule, and 12 or more characters long
        "Akwaaba2026!xyz, STRONG",
        "akwaaba!2026, WEAK",        // ANY broken rule makes it WEAK
    })
    void strength(String password, Strength expected) {
        assertEquals(expected, PasswordChecker.strength(password));
    }

    @Test
    void acceptableMeansNoProblems() {
        assertTrue(PasswordChecker.isAcceptable("Abcdef1!"));
        assertFalse(PasswordChecker.isAcceptable("abcdef1!"));
    }

    @Test
    void nullIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> PasswordChecker.problems(null));
    }
}
