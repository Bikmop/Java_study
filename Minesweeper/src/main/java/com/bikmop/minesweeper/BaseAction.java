package com.bikmop.minesweeper;

/**
 * Базовые действия пользователя
 * Используются интерфейсы без конкретной реализации
 */
public class BaseAction implements UserAction {
    /** Генератор доски */
    private final BoardGenerator generator;
    /** Доска */
    private final Board board;
    /** Логика игры */
    private final MinesweeperLogic logic;

    /**
     * Конструктор
     * @param logic Логика
     * @param board Доска
     * @param generator Генератор
     */
    public BaseAction(final MinesweeperLogic logic, final Board board, final BoardGenerator generator) {
        this.generator = generator;
        this.board = board;
        this.logic = logic;
    }


    /**
     * Инициализация игры:
     *  - генерация доски
     *  - отрисовка доски
     *  - загрузка доски в логику
     */
    public void initGame() {
        final Cell[][] cells = generator.generate();
        this.board.drawBoard(cells);
        this.logic.loadBoard(cells);
    }

    /**
     * Ответ на выбор пользователя
     * @param x Координата Х
     * @param y Координата У
     * @param bomb Наличие мины
     */
    public void select(int x, int y, boolean bomb) {
        this.logic.suggest(x, y, bomb);     // Загрузили выбор пользователя в логику
        this.board.drawCell(x, y);          // Отрисовали выбор пользователя
        if (this.logic.shouldBang(x, y)) {
            this.board.drawBang();          // Проверка на взрыв и его отрисовка
        }
        if (this.logic.finish()) {
            this.board.drawCongratulate();  // Проверка на успешное окончание игры и отрисовка
        }
    }
}
