package org.skillsmart.testutil;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.field.GameField;
import org.skillsmart.field.GameFieldElement;

/**
 * Сборка поля с заданными символами для unit-тестов (задание 12).
 * layout[y][x] — строка y, столбец x; пробел ' ' = пустая клетка.
 */
public final class TestFieldBuilder {

    private TestFieldBuilder() {
    }

    public static GameField fromLayout(char[][] layout) {
        GameField field = new GameField();
        for (int y = 0; y < layout.length; y++) {
            for (int x = 0; x < layout[y].length; x++) {
                char value = layout[y][x];
                FieldCoordinate coordinate = new FieldCoordinate(x, y);
                GameFieldElement element = value == ' '
                        ? GameFieldElement.createEmptyElement()
                        : GameFieldElement.ofUnit(value);
                field.setFieldElement(element, coordinate);
            }
        }
        return field;
    }
}
