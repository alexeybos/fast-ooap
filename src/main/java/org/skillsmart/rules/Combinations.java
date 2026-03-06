package org.skillsmart.rules;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.field.GameField;
import org.skillsmart.field.GameFieldElement;

import java.util.Set;

public class Combinations extends AbstractCombinations {



    @Override
    public void deleteCombination(GameField field, Set<FieldCoordinate> combination) {
        for (FieldCoordinate coordinate: combination) {
            field.setFieldElement(GameFieldElement.createEmptyElement(), coordinate);
        }
    }

    @Override
    public boolean hasMatches(GameField field) {
        Set<FieldCoordinate> matches = getCombinations(field);
        return !matches.isEmpty();
    }

    @Override
    public Set<FieldCoordinate> getCombinations(GameField field) {
        return field.getMatches();
    }

    @Override
    public long getScore(GameField field, Set<FieldCoordinate> combination) {
        long score = 0;
        int i = 0;
        for (FieldCoordinate coordinate: combination) {
            i++;
            score += 10;
            if (i > 3) score += (i - 1) * 10L;
        }
        return score;
    }
}
