package com.makerspace.shop;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

/**
 * A tour of JUnit 5. Each @Test method is one small, independent check.
 * Run them with:  ./mvnw test   (Windows: mvnw test), or with the
 * Testing panel (the flask icon) in VS Code.
 */
class ShoppingCartTest {

    private ShoppingCart cart;

    @BeforeEach                      // runs before EVERY test, so each test gets a fresh cart
    void setUp() {
        cart = new ShoppingCart();
    }

    @Test
    void newCartIsEmpty() {
        assertTrue(cart.isEmpty());
        assertEquals(0, cart.itemCount());
    }

    @Test
    void addingItemsCountsThem() {
        cart.add("LED", 1.50, 10);
        cart.add("Resistor", 0.20, 5);
        assertEquals(15, cart.itemCount());       // assertEquals(EXPECTED, ACTUAL)
        assertFalse(cart.isEmpty());
    }

    @Test
    void addingTheSameItemTwiceAddsTheQuantities() {
        cart.add("LED", 1.50, 10);
        cart.add("LED", 1.50, 5);
        assertEquals(15, cart.itemCount());
    }

    @Test
    void subtotalMultipliesPriceByQuantity() {
        cart.add("Arduino", 180.0, 2);
        cart.add("Breadboard", 25.0, 3);
        assertEquals(435.0, cart.subtotal(), 0.001);   // doubles need a tolerance ("delta")
    }

    @Test
    @DisplayName("The total adds 15% VAT when there's no discount")
    void totalAddsVat() {
        cart.add("Multimeter", 100.0, 1);
        assertEquals(115.0, cart.total(), 0.001);
    }

    @ParameterizedTest(name = "a subtotal of {0} gets a discount of {1}")
    @CsvSource({
        "0, 0",
        "499.99, 0",
        "500, 0.05",          // boundaries are where bugs hide: test exactly AT them
        "999.99, 0.05",
        "1000, 0.10",
        "25000, 0.10",
    })
    void discountRates(double subtotal, double expectedRate) {
        assertEquals(expectedRate, ShoppingCart.discountRate(subtotal), 0.0001);
    }

    @Test
    void zeroQuantityIsRefused() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> cart.add("LED", 1.50, 0));
        assertTrue(e.getMessage().contains("Quantity"));
    }

    @Test
    void removingSomethingNotInTheCartIsRefused() {
        assertThrows(IllegalArgumentException.class, () -> cart.remove("Unicorn"));
    }

    @Test
    void aBigOrderGetsTheDiscountAndVat() {
        cart.add("Raspberry Pi", 1150.0, 1);
        assertAll(                                     // check several things, and report EVERY failure
                () -> assertEquals(1150.0, cart.subtotal(), 0.001),
                () -> assertEquals(0.10, ShoppingCart.discountRate(cart.subtotal()), 0.0001),
                () -> assertEquals(1190.25, cart.total(), 0.001));
    }
}
