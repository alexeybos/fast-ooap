package org.skillsmart.control;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.core.GameEngine;
import org.skillsmart.core.GameStatuses;
import org.skillsmart.data.StatObj;
import org.skillsmart.field.GameField;
import org.skillsmart.field.GameFieldElement;
import org.skillsmart.rules.Combinations;

import java.util.Set;

public class UserTurnCommand implements GameCommand {

    GameEngine game;
    GameField field;
    FieldCoordinate from;
    FieldCoordinate to;

    public UserTurnCommand(GameEngine game, FieldCoordinate from, FieldCoordinate to) {
        this.game = game;
        this.field = game.getGameField();
        this.from = from;
        this.to = to;
    }

    @Override
    public void execute() {
        if (game.getGameStatus() != GameStatuses.USER_TURN_WAIT) return;
        if (!checkTurnValid()) {
            System.out.println("Некорректный ход. Повторите ввод.");
            return;
        }
        elementExchange();
        System.out.println("Ход сделан.");
        field.show();
        Combinations combinations = new Combinations();
        if (!combinations.hasMatches(field)) {
            System.out.println("Некорректный ход. Комбинаций не найдено.");
            undo();
            field.show();
            return;
        }
        Set<FieldCoordinate> matches = combinations.getCombinations(field);
        long points = combinations.getScore(field, matches);
        combinations.deleteCombination(field, matches);
        StatObj stats = game.getStats();
        stats.setScore(stats.getScore() + points);
        System.out.println("Удаляю совпадения.");
        field.show();
        field.fallElements();
        System.out.println("Элементы падают.");
        field.show();
        field.fill();
        System.out.println("Заполняю пустоту.");
        field.settleField();
        field.show();
    }

    @Override
    public void undo() {
        elementExchange();
    }

    private void elementExchange() {
        GameFieldElement forChange = field.getFieldElement(from);
        field.setFieldElement(field.getFieldElement(to), from);
        field.setFieldElement(forChange, to);
    }

    private boolean checkTurnValid() {
        int offsetX = Math.abs(from.getX()-to.getX());
        int offsetY = Math.abs(from.getY()-to.getY());
        if ((offsetX == 1 && offsetY == 0) || (offsetX == 0 && offsetY == 1)) return true;

        return false;
    }

}
