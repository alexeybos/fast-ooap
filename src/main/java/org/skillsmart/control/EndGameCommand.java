package org.skillsmart.control;

import org.skillsmart.core.GameEngine;
import org.skillsmart.core.GameStatuses;

public class EndGameCommand implements GameCommand {

    private final GameEngine game;

    public EndGameCommand(GameEngine game) {
        this.game = game;
    }

    @Override
    public void execute() {
        //показать статистику
        System.out.println("Вы заработали " + game.getStats().getScore() + " очков. Возвращайтесь!");
        game.setGameStatus(GameStatuses.NOT_STARTED);
    }

    @Override
    public void undo() {

    }
}
