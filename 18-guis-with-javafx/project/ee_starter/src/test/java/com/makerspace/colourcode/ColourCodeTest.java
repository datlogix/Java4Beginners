package com.makerspace.colourcode;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/** The model can be tested without a window. Add tests for describe and encode. */
class ColourCodeTest {

    @Test
    void yellowVioletRedIs4700() {
        assertEquals(4700, ColourCode.ohms(BandColour.YELLOW, BandColour.VIOLET, BandColour.RED), 1e-9);
    }

    @Test
    void goldMultiplierGivesDecimals() {
        assertEquals(3.3, ColourCode.ohms(BandColour.ORANGE, BandColour.ORANGE, BandColour.GOLD), 1e-9);
    }
}
