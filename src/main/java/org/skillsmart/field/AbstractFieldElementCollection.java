package org.skillsmart.field;

import org.skillsmart.core.FieldCoordinate;

import java.util.List;

public abstract class AbstractFieldElementCollection {

    // конструкторы

    // команды

    //
    // предусловие:
    // постусловие:
    public abstract void addElement(FieldCoordinate coordinate);

    public abstract void deleteElement(FieldCoordinate coordinate);

    // запросы
    // предусловие:
    public abstract List<FieldCoordinate> getEmptySlots();

    // проверка валидности координат
    public abstract boolean isCoordinateValid(FieldCoordinate coordinate);
}
