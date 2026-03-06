package org.skillsmart.rules;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.field.GameField;

import java.util.List;
import java.util.Set;

public abstract class AbstractCombinations {

    // конструкторы

    // команды

    // удаление комбинации
    // предусловие: на поле есть комбинации
    // постусловие: элементы поля очищены и/или заменены на бонус
    public abstract void deleteCombination(GameField field, Set<FieldCoordinate> combination);

    // запросы

    // определить, есть ли хотя бы одна комбинация на поле
    // предусловие: поле заполнено (статусы "refilled", "ход сделан")
    public abstract boolean hasMatches(GameField field);

    // получить все комбинации на поле
    // предусловие: на поле есть комбинации
    public abstract Set<FieldCoordinate> getCombinations(GameField field);

    // получить очки за комбинацию
    // предусловие: на поле есть комбинации
    public abstract long getScore(GameField field, Set<FieldCoordinate> combination);

}
