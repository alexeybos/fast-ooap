package org.skillsmart.data;

public abstract class AbstractScoreCounter {

    // конструкторы

    // команды

    // добавить очки
    // предусловие:
    // постусловие: сумма очков увеличилась на score
    public abstract void addScore(long score);

    // отобрать очки (возможно за что-то)
    // предусловие:
    // постусловие: сумма очков уменьшилась на score, но не меньше 0
    public abstract void removeScore(long score);

    // сбросить очки
    // предусловие:
    // постусловие: сумма очков уменьшилась до 0
    public abstract void resetScore();

    // запросы

    // получить очки
    // предусловие:
    public abstract long getScore();

}
