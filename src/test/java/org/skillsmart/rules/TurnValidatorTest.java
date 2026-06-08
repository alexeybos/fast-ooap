package org.skillsmart.rules;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.field.GameField;
import org.skillsmart.testutil.TestFieldBuilder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TurnValidatorTest {

    private TurnValidator validator;

    @BeforeEach
    void setUp() {
        validator = new TurnValidator();
    }

    @Test
    void isValid_returnsTrue_whenSwapCreatesHorizontalMatch() {
        // Обмен (1,0)↔(0,1): G R G / G _ _  →  G G G на первой строке
        GameField field = TestFieldBuilder.fromLayout(new char[][]{
                {'G', 'R', 'G'},
                {'G', ' ', ' '},
                {' ', ' ', ' '}
        });

        assertTrue(validator.isValid(field, new FieldCoordinate(1, 0), new FieldCoordinate(0, 1)));
    }

    @Test
    void isValid_returnsFalse_whenSwapDoesNotCreateMatch() {
        GameField field = TestFieldBuilder.fromLayout(new char[][]{
                {'A', 'B', 'C'},
                {'B', 'A', 'C'},
                {'D', 'E', 'F'}
        });

        assertFalse(validator.isValid(field, new FieldCoordinate(1, 0), new FieldCoordinate(2, 0)));
    }

    @Test
    void isValid_returnsFalse_whenCellsAreNotAdjacent() {
        GameField field = TestFieldBuilder.fromLayout(new char[][]{
                {'A', 'B', 'C'},
                {'D', 'E', 'F'},
                {'G', 'H', 'I'}
        });

        assertFalse(validator.isValid(field, new FieldCoordinate(0, 0), new FieldCoordinate(0, 2)));
    }

    @Test
    void isValid_returnsFalse_whenFromCellIsEmpty() {
        GameField field = TestFieldBuilder.fromLayout(new char[][]{
                {' ', 'B', 'C'},
                {'A', 'B', 'C'},
                {'D', 'E', 'F'}
        });

        assertFalse(validator.isValid(field, new FieldCoordinate(0, 0), new FieldCoordinate(1, 0)));
    }

    @Test
    void isValid_doesNotMutateField_whenMoveIsInvalid() {
        GameField field = TestFieldBuilder.fromLayout(new char[][]{
                {'A', 'B', 'C'},
                {'B', 'A', 'C'},
                {'D', 'E', 'F'}
        });
        char before = field.getFieldElement(new FieldCoordinate(1, 0)).getValue();

        validator.isValid(field, new FieldCoordinate(1, 0), new FieldCoordinate(2, 0));

        assertEqualsChar('B', field.getFieldElement(new FieldCoordinate(1, 0)).getValue());
        assertEqualsChar(before, field.getFieldElement(new FieldCoordinate(1, 0)).getValue());
    }

    private static void assertEqualsChar(char expected, char actual) {
        assertTrue(expected == actual, () -> "expected '" + expected + "' but was '" + actual + "'");
    }
}
