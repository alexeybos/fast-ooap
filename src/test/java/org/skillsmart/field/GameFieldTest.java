package org.skillsmart.field;

import org.junit.jupiter.api.Test;
import org.skillsmart.core.FieldCoordinate;

import static org.junit.jupiter.api.Assertions.*;

class GameFieldTest {

    @Test
    void createGameField_no_param_constructor() {
        GameField field = new GameField();
        GameFieldElement elem = field.getFieldElement(new FieldCoordinate(0, 7));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(7, 0));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(7, 7));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(0, 8));
        assertNull(elem);
        elem = field.getFieldElement(new FieldCoordinate(8, 0));
        assertNull(elem);
    }

    @Test
    void createGameField_size_param_constructor() {
        GameField field = new GameField(3);
        GameFieldElement elem = field.getFieldElement(new FieldCoordinate(0, 2));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(2, 0));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(2, 2));
        assertEquals(GameElementType.EMPTY, elem.getElementType());
        elem = field.getFieldElement(new FieldCoordinate(0, 3));
        assertNull(elem);
        elem = field.getFieldElement(new FieldCoordinate(3, 0));
        assertNull(elem);
        elem = field.getFieldElement(new FieldCoordinate(3, 3));
        assertNull(elem);
    }

    @Test
    void createGameField_less_than_min() {
        assertThrows(IllegalArgumentException.class, () -> new GameField(2));
    }

    @Test
    void createGameField_grater_than_max() {
        assertThrows(IllegalArgumentException.class, () -> new GameField(10));
    }

    @Test
    void fill_max() {
        int size = 8;
        GameField field = new GameField(size);
        field.fill();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertNotEquals(GameElementType.EMPTY,field.getFieldElement(new FieldCoordinate(j, i)).getElementType(),
                        "Ячейка (" + j + "," + i + ") не должна быть пустой");
            }
        }
    }

    @Test
    void fill() {
        int size = 4;
        GameField field = new GameField(size);
        field.fill();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                assertNotEquals(GameElementType.EMPTY,field.getFieldElement(new FieldCoordinate(j, i)).getElementType(),
                        "Ячейка (" + j + "," + i + ") не должна быть пустой");
            }
        }
    }

    @Test
    void createCoordinateFromUserInput_exists() {
        GameField field = new GameField(3);
        FieldCoordinate coordinate = field.createCoordinateFromUserInput("a3");
        assertEquals(0, coordinate.getX());
        assertEquals(2, coordinate.getY());
        coordinate = field.createCoordinateFromUserInput("c1");
        assertEquals(2, coordinate.getX());
        assertEquals(0, coordinate.getY());
    }

    @Test
    void createCoordinateFromUserInput_not_exists() {
        GameField field = new GameField(3);
        assertNull(field.createCoordinateFromUserInput("a12"));
        assertNull(field.createCoordinateFromUserInput("a4"));
        assertNull(field.createCoordinateFromUserInput("d1"));
    }

}