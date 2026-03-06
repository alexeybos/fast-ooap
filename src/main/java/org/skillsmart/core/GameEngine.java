package org.skillsmart.core;

import org.skillsmart.control.*;
import org.skillsmart.data.StatObj;
import org.skillsmart.field.GameField;

import java.util.Scanner;

public class GameEngine extends AbstractGameEngine {

    private GameStatuses gameStatus;
    private GameField field;
    private CommandHandler commandHandler;
    private StatObj stats;

    public GameEngine() {
        this.gameStatus = GameStatuses.NOT_STARTED;
        this.commandHandler = new CommandHandler(this);
        this.stats = new StatObj();
        System.out.println("------------------------------");
        System.out.println("Приветствуем в игре три-в-ряд!");
        System.out.println("------------------------------");
    }

    @Override
    public void start() {
        if (getGameStatus() != GameStatuses.NOT_STARTED) return;
        field = new GameField();
        GameCommand command = new StartGameCommand(this);
        command.execute();
        gameStatus = GameStatuses.USER_TURN_WAIT;
    }

    @Override
    public void stop() {
        if (getGameStatus() == GameStatuses.NOT_STARTED) return;
        //вывод статистики на экран
        gameStatus = GameStatuses.NOT_STARTED;
    }

    @Override
    public void userTurnProcess(FieldCoordinate coordinateFrom, FieldCoordinate coordinateTo) {
        if (getGameStatus() != GameStatuses.USER_TURN_WAIT) return;
        gameStatus = GameStatuses.TURN_IS_CHECKING;
        if (!isTurnValid(coordinateFrom, coordinateTo)) {
            // откат хода
        }
        //ожидание хода игрока
        gameStatus = GameStatuses.USER_TURN_WAIT;
    }

    @Override
    public GameStatuses getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatuses status) {
        this.gameStatus = status;
    }

    @Override
    public boolean isTurnValid(FieldCoordinate coordinateFrom, FieldCoordinate coordinateTo) {
        return false;
    }

    @Override
    public boolean isGameOver() {
        return false;
    }

    public GameField getGameField() {
        return field;
    }

    public void setField(GameField field) {
        this.field = field;
    }

    /*public void handleInput(String userInput) {
        if (gameStatus == GameStatuses.TURN_IS_CHECKING) return;
        if (userInput.equalsIgnoreCase("start")) {
            start();
        }
    }*/

    private String waitAndGetUserCommand() {
        Scanner input = new Scanner(System.in);
        System.out.print("Введите команду: /");
        String userCommand = input.nextLine();
        input.close();
        return userCommand;
    }

    public void launchEngine() {
        commandHandler.execute(new HelpCommand());
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Введите команду: ");
            String input = scanner.nextLine().trim();

            commandHandler.execute(input);

            if (gameStatus == GameStatuses.EXIT) {
                System.out.println("До свидания!");
                break;
            }
        }
        scanner.close();
    }

    public StatObj getStats() {
        return stats;
    }
}
