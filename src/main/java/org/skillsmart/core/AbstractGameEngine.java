package org.skillsmart.core;

public abstract class AbstractGameEngine {

    // конструкторы

    // команды

    // Запуск игры
    // предусловие: игра не запущена (статус "NOT_STARTED")
    // постусловие: игра запущена (статус "USER_TURN_WAIT")
    public abstract void start();

    // окончание игры
    // предусловие: игра запущена (статус из блока "действующих" статусов)
    // постусловие: игра не запущена /или остановлена/ (статус "NOT_STARTED" или "ENDED")
    public abstract void stop();

    // обработка хода игрока
    // предусловие: статус (пока так думаю) ход в обработке (статус "TURN_IS_CHECKING")
    // постусловие: статус ожидание хода или игра завершена (статус "USER_WAITING" или "ENDED")
    public abstract void userTurnProcess(FieldCoordinate coordinateFrom, FieldCoordinate coordinateTo);

    // запросы

    // получение статуса игры
    // предусловие:
    public abstract GameStatuses getGameStatus();

    // проверка хода игрока
    // предусловие: статус ход в обработке "TURN_IS_CHECKING"
    public abstract boolean isTurnValid(FieldCoordinate coordinateFrom, FieldCoordinate coordinateTo);

    // проверка кончились ли ходы игрока
    // предусловие: игра запущена
    public abstract boolean isGameOver();
}
