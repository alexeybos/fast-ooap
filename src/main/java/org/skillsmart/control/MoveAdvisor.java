package org.skillsmart.control;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.core.GameEngine;
import org.skillsmart.field.GameField;
import org.skillsmart.field.GameFieldElement;
import org.skillsmart.rules.Combinations;

import java.util.ArrayList;
import java.util.List;

public class MoveAdvisor {

    private static final int FIELD_SIZE = 8;
    private static final char[] X_COORDS = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    private final GameEngine game;

    public MoveAdvisor(GameEngine game) {
        this.game = game;
    }

    public List<String> findAvailableMoves() {
        List<String> moves = new ArrayList<>();
        GameField field = game.getGameField();
        if (field == null) {
            return moves;
        }

        for (int y = 0; y < FIELD_SIZE; y++) {
            for (int x = 0; x < FIELD_SIZE; x++) {
                addMoveIfValid(field, moves, x, y, x + 1, y);
                addMoveIfValid(field, moves, x, y, x, y + 1);
            }
        }

        return moves;
    }

    private void addMoveIfValid(GameField field, List<String> moves, int fromX, int fromY, int toX, int toY) {
        if (!isInsideField(toX, toY)) {
            return;
        }

        FieldCoordinate from = new FieldCoordinate(fromX, fromY);
        FieldCoordinate to = new FieldCoordinate(toX, toY);

        swap(field, from, to);
        boolean isMatchCreated = new Combinations().hasMatches(field);
        swap(field, from, to);

        if (isMatchCreated) {
            moves.add(toDisplay(from) + " " + toDisplay(to));
        }
    }

    private static boolean isInsideField(int x, int y) {
        return x >= 0 && x < FIELD_SIZE && y >= 0 && y < FIELD_SIZE;
    }

    private static void swap(GameField field, FieldCoordinate first, FieldCoordinate second) {
        GameFieldElement temp = field.getFieldElement(first);
        field.setFieldElement(field.getFieldElement(second), first);
        field.setFieldElement(temp, second);
    }

    private static String toDisplay(FieldCoordinate coordinate) {
        return String.valueOf(X_COORDS[coordinate.getX()]) + (coordinate.getY() + 1);
    }
}
