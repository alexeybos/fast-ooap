package org.skillsmart.field;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.rules.Combinations;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class GameField extends AbstractGameField {

    private static final int FIELD_SIZE = 8;
    private static final char[] rulerX = {'a','b','c','d','e','f','g','h'};
    private static final int[] rulerY = {1, 2, 3, 4, 5, 6, 7, 8};
    private FieldElementCollection elements;

    public GameField() {
        elements = new FieldElementCollection();
    }

    @Override
    public void fill() {
        for (int i = 0; i < FIELD_SIZE; i++) {
            for (int j = 0; j < FIELD_SIZE; j++) {
                if (elements.getElement(new FieldCoordinate(j, i)).getElementType() == GameElementType.EMPTY) {
                    elements.addElement(new FieldCoordinate(j, i));
                }
            }
        }
    }

    @Override
    public void refill() {

    }

    @Override
    public boolean is_turn_possible() {
        return false;
    }

    public void show() {
        System.out.print(' ');
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(' ');
            System.out.print(rulerX[i]);
        }
        System.out.println();
        for (int i = 0; i < FIELD_SIZE; i++) {
            System.out.print(rulerY[i]);
            for (int j = 0; j < FIELD_SIZE; j++) {
                System.out.print(' ');
                System.out.print(elements.getElement(new FieldCoordinate(j, i)).getValue());
            }
            System.out.println();
        }
    }

    public GameFieldElement getFieldElement(FieldCoordinate coordinate) {
        return elements.getElement(coordinate);
    }

    public void setFieldElement(GameFieldElement elem, FieldCoordinate coordinate) {
        elements.setElement(elem, coordinate);
    }

    public FieldCoordinate createCoordinateFromUserInput(String input) {
        if (input.length() != 2) return null;
        int x = Arrays.binarySearch(rulerX, input.toLowerCase().charAt(0));
        if (x == -1) return null;
        int y = Character.getNumericValue(input.charAt(1)) - 1;
        if (y < 0 || y >= FIELD_SIZE) return null;
        return new FieldCoordinate(x, y);
    }

    public Set<FieldCoordinate> getMatches() {
        return elements.getMatches();
    }

    public void fallElements() {
        elements.fallElementsToEmptySlots();
    }

    public void settleField() {
        System.out.println("Успокаиваем поле...");
        Combinations combinations = new Combinations();
        while (combinations.hasMatches(this)) {
            combinations.deleteCombination(this, combinations.getCombinations(this));
            fallElements();
            fill();
        }
    }

}
