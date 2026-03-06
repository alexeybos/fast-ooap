package org.skillsmart.control;

public class HelpCommand implements GameCommand {
    @Override
    public void execute() {
        //helpCommand
        System.out.println("Доступные команды: ");
        System.out.println("start - старт игры");
        System.out.println("A1 A2 - ход игрока: передвинет элемент с поля A1 на поле A2");
        System.out.println("end - окончание игры");
        System.out.println("exit - выход из игры");
        System.out.println("help - список доступных команд");
        System.out.println();
    }

    @Override
    public void undo() {

    }
}
