package org.skillsmart.field;

import org.skillsmart.core.FieldCoordinate;

public abstract class AbstractGameFieldElement {

    // конструкторы
    // постусловие: сгенерирован игровой элемент
    //public GameFieldElement();
    // команды

    // разместить элемент на поле
    // предусловие:
    // постусловие: элементу присвоены координаты на поле
    public abstract void setElementToField(FieldCoordinate coordinate);

    // удалить элемент с поля
    // предусловие: элементу присвоены координаты на поле
    // постусловие: элемент удален (координаты элемента очищены/удален вообще из памяти)
    public abstract void removeElementFromField();

    // запросы

    // получить тип элемента
    // предусловие:
    public abstract GameElementType getElementType();

    // получить координаты элемента
    // предусловие:
    public abstract FieldCoordinate getElementCoordinate();

}
