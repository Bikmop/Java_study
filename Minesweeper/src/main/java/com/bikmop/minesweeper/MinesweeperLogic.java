package com.bikmop.minesweeper;

/**
 * Интерфейс логики игры, которая может изменяться
 */
public interface MinesweeperLogic {

    /**
     * Загрузка доски для вычислений
     * @param cells Ячейки
     */
    void loadBoard(Cell[][] cells);

    /**
     * Проверка необходимости взрыва в зависимости от координат
     * Можем изменять условия срабатывания мины
     * @param x Координата Х
     * @param y Координата У
     * @return Нужен ли взрыв
     */
    boolean shouldBang(int x, int y);

    /**
     * Проверка успешного окончания игры
     * @return Успешное окончание
     */
    boolean finish();

    /**
     * Пришедшее событие от пользователя. Его мнение о наличии/отсутствии мины
     * @param x Координата Х
     * @param y Координата У
     * @param bomb Мина или пусто
     */
    void suggest(int x, int y, boolean bomb);

}
