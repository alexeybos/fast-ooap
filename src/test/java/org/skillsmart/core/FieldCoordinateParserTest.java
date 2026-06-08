package org.skillsmart.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FieldCoordinateParserTest {

    @ParameterizedTest(name = "parse(\"{0}\") -> ({1}, {2})")
    @CsvSource({
            "a1, 0, 0",
            "h8, 7, 7",
            "A1, 0, 0",
            "b4, 1, 3"
    })
    void parse_returnsCoordinate_forValidInput(String input, int expectedX, int expectedY) {
        Optional<FieldCoordinate> result = FieldCoordinateParser.parse(input);

        assertTrue(result.isPresent());
        assertEquals(expectedX, result.get().getX());
        assertEquals(expectedY, result.get().getY());
    }

    @ParameterizedTest(name = "parse(\"{0}\") -> empty")
    @ValueSource(strings = {"z1", "a0", "a9", "", "abc", "1a"})
    void parse_returnsEmpty_forInvalidInput(String input) {
        assertTrue(FieldCoordinateParser.parse(input).isEmpty());
    }

    @Test
    void parse_returnsEmpty_forNullInput() {
        assertTrue(FieldCoordinateParser.parse(null).isEmpty());
    }
}
