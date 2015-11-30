package com.bikmop.minesweeper;

/**
 * Реализует отдельную ячейку доски
 * @param <T> Класс для отрисовки
 */
public interface Cell<T> {
    /**
     * Является ли миной на самом деле
     * @return Мина или нет
     */
    boolean isBomb();

    /**
     * Пользователь предполагает, что мина
     * @return Предположительно мина
     */
    boolean isSuggestBomb();

    /**
     * Пользователь предполагает, что пустая
     * @return Предположительно пустая
     */
    boolean isSuggestEmpty();

    /**
     * Установить значение "пустая"
     */
    void suggestEmpty();

    /**
     * Установить значение "мина"
     */
    void suggestBomb();

    /**
     * Отрисовка ячейки
     * @param paint Вспомогательный класс для отрисовки
     * @param real Рисовать то, что есть или предположение пользователя
     */
    void draw(T paint, boolean real);

    /**
     * Отрисовка ячейки на заданных координатах
     * @param paint Вспомогательный класс для отрисовки
     * @param x Координата X на paint
     * @param y Координата Y на paint
     * @param real Рисовать то, что есть или предположение пользователя
     */
    void draw(T paint, int x, int y, int cellSize, boolean real);


}
