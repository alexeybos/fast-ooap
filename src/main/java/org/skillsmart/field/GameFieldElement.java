package org.skillsmart.field;

import org.skillsmart.core.FieldCoordinate;

public class GameFieldElement extends AbstractGameFieldElement {

    private static final char[] UNITS = new char[]{'A','B','C','D','E'};
    private static final char BONUS = 'Z';
    private static final char EMPTY = ' ';
    private char value;
    private final GameElementType type;
    private FieldCoordinate coordinate;

    public GameFieldElement() {
        int ind = (int) (Math.random() * 5);
        this.value = UNITS[ind];
        this.type = GameElementType.UNIT;
    }

    private GameFieldElement(GameElementType type, char value) {
        this.value = value;
        this.type = type;
    }

    public static GameFieldElement createBonusElement() {
        return new GameFieldElement(GameElementType.BONUS, BONUS);
    }

    public static GameFieldElement createEmptyElement() {
        return new GameFieldElement(GameElementType.EMPTY, EMPTY);
    }

    @Override
    public void setElementToField(FieldCoordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public void removeElementFromField() {
        this.coordinate = null;
    }

    @Override
    public GameElementType getElementType() {
        return type;
    }

    @Override
    public FieldCoordinate getElementCoordinate() {
        return coordinate;
    }

    public char getValue() {
        return value;
    }

}
