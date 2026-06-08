package org.skillsmart.field;

import org.junit.jupiter.api.Test;
import org.skillsmart.core.FieldCoordinate;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FieldElementCollectionTest {

    @Test
    void createElementCollection_positive_no_param_constructor() {
        FieldElementCollection elemCollection = new FieldElementCollection();
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(0, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(1, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(2, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(3, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(4, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(5, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(6, 7)), "Field should have 8 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(7, 7)), "Field should have 8 slots");
        assertFalse(elemCollection.isCoordinateValid(new FieldCoordinate(8, 8)), "Field should have only 8 slots");
    }

    @Test
    void createElementCollection_positive_size_param_constructor() {
        FieldElementCollection elemCollection = new FieldElementCollection(5);
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(0, 4)), "Field should have 5 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(1, 4)), "Field should have 5 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(2, 4)), "Field should have 5 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(3, 4)), "Field should have 5 slots");
        assertTrue(elemCollection.isCoordinateValid(new FieldCoordinate(4, 4)), "Field should have 5 slots");
        assertFalse(elemCollection.isCoordinateValid(new FieldCoordinate(5, 5)), "Field should have only 5 slots");
    }

    @Test
    void createElementCollection_less_than_min() {
        assertThrows(IllegalArgumentException.class, () -> new FieldElementCollection(2));
    }

    @Test
    void createElementCollection_grater_than_max() {
        assertThrows(IllegalArgumentException.class, () -> new FieldElementCollection(10));
    }

    @Test
    void isCoordinateValid_cornerCoordinates() {
        FieldElementCollection col = new FieldElementCollection(5);
        assertTrue(col.isCoordinateValid(new FieldCoordinate(0, 0)));
        assertTrue(col.isCoordinateValid(new FieldCoordinate(4, 4)));
        assertTrue(col.isCoordinateValid(new FieldCoordinate(0, 4)));
        assertTrue(col.isCoordinateValid(new FieldCoordinate(4, 0)));
    }
    @Test
    void isCoordinateValid_outOfBounds() {
        FieldElementCollection col = new FieldElementCollection(5);
        assertFalse(col.isCoordinateValid(new FieldCoordinate(5, 0)));
        assertFalse(col.isCoordinateValid(new FieldCoordinate(0, 5)));
        assertFalse(col.isCoordinateValid(new FieldCoordinate(-1, 0)));
        assertFalse(col.isCoordinateValid(new FieldCoordinate(0, -1)));
    }

    @Test
    void addElement_validCoordinate() {
        FieldElementCollection col = new FieldElementCollection(4);
        FieldCoordinate coord = new FieldCoordinate(1, 2);
        col.addElement(coord);
        assertNotEquals(GameElementType.EMPTY, col.getElement(coord).getElementType());
    }
    @Test
    void addElement_invalidCoordinate_noThrows() {
        FieldElementCollection col = new FieldElementCollection(4);
        assertDoesNotThrow(() -> col.addElement(new FieldCoordinate(10, 10)));
    }

    @Test
    void getMatches_threeInRowX() {
        FieldElementCollection col = new FieldElementCollection(3);
        GameFieldElement elem1 = new GameFieldElement(GameElementType.UNIT, 'A');
        col.setElement(elem1, new FieldCoordinate(0, 0));
        col.setElement(elem1, new FieldCoordinate(0, 1));
        col.setElement(elem1, new FieldCoordinate(0, 2));
        Set<FieldCoordinate> matches = col.getMatches();
        assertTrue(matches.contains(new FieldCoordinate(0, 0)));
        assertTrue(matches.contains(new FieldCoordinate(0, 1)));
        assertTrue(matches.contains(new FieldCoordinate(0, 2)));
    }
}