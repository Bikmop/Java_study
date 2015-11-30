package com.bikmop.minesweeper;

/**
 * Действие пользователя
 */
public interface UserAction {

    /**
     * Инициализация игры
     */
    void initGame();

    /**
     * Выбор ячейки и наличия мины
     * @param x Координата Х
     * @param y Координата У
     * @param bomb Наличие мины
     */
    void select(int x, int y, boolean bomb);

}
