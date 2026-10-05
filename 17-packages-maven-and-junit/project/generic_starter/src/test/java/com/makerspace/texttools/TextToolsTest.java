package com.makerspace.texttools;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import org.junit.jupiter.api.Test;

class TextToolsTest {

    @Test
    void wordsAreLowerCaseWithoutPunctuation() {
        assertEquals(List.of("hello", "world"), TextTools.words("Hello, World!"));
    }

    @Test
    void blankTextHasNoWords() {
        assertEquals(List.of(), TextTools.words("  ...  "));
    }

    // TODO: at least 20 tests altogether, covering every public method
}
