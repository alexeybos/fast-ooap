package org.skillsmart.data;

public abstract class AbstractGameStatistics {

    // конструкторы

    // команды

    // записать статистическую информацию
    // предусловие:
    // постусловие: к статистике добавлена новая информация
    public abstract void appendStat(StatObj stat);

    // очистить статистическую информацию
    // предусловие:
    // постусловие: статистика обнулена
    public abstract void deleteStat();

    // запросы
    // получить статистическую информацию
    // предусловие:
    public abstract StatObj[] getStat(StatObj stat);

}
