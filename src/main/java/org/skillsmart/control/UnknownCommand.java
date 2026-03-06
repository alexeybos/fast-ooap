package org.skillsmart.control;

public class UnknownCommand implements GameCommand {

    @Override
    public void execute() {
        System.out.println("Некорректная команда. Для списка доступных команд введите help");
    }

    @Override
    public void undo() {

    }
}
