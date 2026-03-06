package org.skillsmart.control;

import org.skillsmart.core.GameEngine;
import org.skillsmart.core.GameStatuses;

public class ExitCommand implements GameCommand {

    private final GameEngine game;

    public ExitCommand(GameEngine game) {
        this.game = game;
    }

    @Override
    public void execute() {
        //показать статистику
        System.out.println("Вы заработали " + game.getStats().getScore() + " очков. Возвращайтесь!");
        game.setGameStatus(GameStatuses.EXIT);
    }

    @Override
    public void undo() {

    }
}
