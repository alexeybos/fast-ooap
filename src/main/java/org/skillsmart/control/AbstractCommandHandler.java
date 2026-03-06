package org.skillsmart.control;

public abstract class AbstractCommandHandler {

    // конструкторы

    // команды

    // выполнить команду
    // предусловие:
    // постусловие:
    public abstract void execute(String inputCommand);

    // отменить последний ход
    // предусловие: статус "TURN_IS_CHECKING"
    // постусловие: статус "USER_WAITING"
    public abstract void undoLastTurn();

    // запросы
    // получить историю команд
    public abstract GameCommand[] getCommandHistory();

}
