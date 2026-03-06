package org.skillsmart.control;

import org.skillsmart.core.GameEngine;
import org.skillsmart.core.GameStatuses;
import org.skillsmart.field.GameField;

public class StartGameCommand  implements GameCommand {

    private final GameEngine game;

    public StartGameCommand(GameEngine game) {
        this.game = game;
    }

    @Override
    public void execute() {
        if (game.getGameStatus() != GameStatuses.NOT_STARTED) return;
        //отрисовка и заполнение поля
        GameField field = new GameField();
        game.getStats().setScore(0L);
        field.fill();
        field.settleField();
        field.show();
        game.setField(field);
        //ожидание хода игрока
        game.setGameStatus(GameStatuses.USER_TURN_WAIT);
    }

    @Override
    public void undo() {

    }
}
