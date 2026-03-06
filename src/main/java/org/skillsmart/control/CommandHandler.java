package org.skillsmart.control;

import org.skillsmart.core.FieldCoordinate;
import org.skillsmart.core.GameEngine;

import java.util.Stack;

public class CommandHandler extends AbstractCommandHandler {

    private final GameEngine game;
    private Stack<GameCommand> commandHist;

    public CommandHandler(GameEngine game) {
        this.game = game;
        this.commandHist = new Stack<>();
    }

    @Override
    public void execute(String inputCommand) {
        GameCommand command = defineCommand(inputCommand);
        execute(command);
    }

    public void execute(GameCommand command) {
        commandHist.push(command);
        command.execute();
    }

    @Override
    public void undoLastTurn() {
        GameCommand command = commandHist.peek();
        if (command instanceof UserTurnCommand) commandHist.pop().undo();
    }

    @Override
    public GameCommand[] getCommandHistory() {
        return new GameCommand[0];
    }

//    public GameCommand

    private GameCommand defineCommand(String inputCommand) {
        if (inputCommand.equalsIgnoreCase("start")) {
            return new StartGameCommand(game);
        }
        if (inputCommand.equalsIgnoreCase("exit")) {
            return new ExitCommand(game);
        }
        if (inputCommand.equalsIgnoreCase("end")) {
            return new EndGameCommand(game);
        }

        //возможно это ход
        String[] parts = inputCommand.split(" ");
        if (parts.length == 2) {
            FieldCoordinate coordFrom = game.getGameField().createCoordinateFromUserInput(parts[0]);
            FieldCoordinate coordTo = game.getGameField().createCoordinateFromUserInput(parts[1]);
            return new UserTurnCommand(game, coordFrom, coordTo);
        }

        return new UnknownCommand();
    }
}
