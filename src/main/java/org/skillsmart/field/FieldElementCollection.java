package org.skillsmart.field;

import org.skillsmart.core.FieldCoordinate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FieldElementCollection extends AbstractFieldElementCollection {

    private final int size = 8;

    private GameFieldElement[][] elements;
    //FIXME тут конечно нужен size
    public FieldElementCollection() {
        elements = new GameFieldElement[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                elements[i][j] = GameFieldElement.createEmptyElement();
            }
        }
    }

    @Override
    public void addElement(FieldCoordinate coordinate) {
        if (!isCoordinateValid(coordinate)) return;
        elements[coordinate.getX()][coordinate.getY()] = new GameFieldElement();
    }

    @Override
    public void deleteElement(FieldCoordinate coordinate) {
        //все сверху падает и генерируется новый элемент
    }

    @Override
    public List<FieldCoordinate> getEmptySlots() {
        return List.of();
    }

    @Override
    public boolean isCoordinateValid(FieldCoordinate coordinate) {
        return coordinate.getX() >= 0 && coordinate.getX() <= size && coordinate.getY() >= 0 && coordinate.getY() <= size;
    }

    public GameFieldElement getElement(FieldCoordinate coordinate) {
        return elements[coordinate.getX()][coordinate.getY()];
    }

    public void setElement(GameFieldElement elem, FieldCoordinate coordinate) {
        elements[coordinate.getX()][coordinate.getY()] = elem;
    }

    public Set<FieldCoordinate> getMatches() {
        Set<FieldCoordinate> result = new HashSet<>();
        // по х
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 2; j++) {
                if (elements[i][j].getValue() == elements[i][j + 1].getValue() &&
                        elements[i][j].getValue() == elements[i][j + 2].getValue()) {
                    result.add(new FieldCoordinate(i, j));
                    result.add(new FieldCoordinate(i, j + 1));
                    result.add(new FieldCoordinate(i, j + 2));
                }
            }
        }
        // по у
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size - 2; i++) {
                if (elements[i][j].getValue() == elements[i + 1][j].getValue() &&
                        elements[i][j].getValue() == elements[i + 2][j].getValue()) {
                    result.add(new FieldCoordinate(i, j));
                    result.add(new FieldCoordinate(i + 1, j));
                    result.add(new FieldCoordinate(i + 2, j));
                }
            }
        }
        return result;
    }

    public void fallElementsToEmptySlots() {
        for (int i = 0; i < size; i++) {
            for (int j = size - 1; j > 0; j--) {
                if (elements[i][j].getElementType() == GameElementType.EMPTY) {
                    for (int k = j - 1; k >= 0; k--) {
                        if (elements[i][k].getElementType() != GameElementType.EMPTY) {
                            elements[i][j] = elements[i][k];
                            elements[i][k] = GameFieldElement.createEmptyElement();
                            break;
                        }
                    }
                }
            }
        }
    }
}
