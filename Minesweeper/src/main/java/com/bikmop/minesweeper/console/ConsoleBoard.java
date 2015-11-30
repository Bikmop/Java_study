package com.bikmop.minesweeper.console;

import com.bikmop.minesweeper.Board;
import com.bikmop.minesweeper.Cell;

/**
 * Консольная реализация доски игры
 */
public class ConsoleBoard implements Board {
    /**
     * Игровые ячейки
     */
    private Cell[][] cells;

    /**
     * Нарисовать доску с предположениями пользователя (redraw(false))
     * @param cells Массив ячеек
     */
    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
        this.redraw(false);
    }

    /**
     * Нарисовать ячейку (в консоль будет перерисовываться вся доска с предположениями пользователя)
     * @param x Позиция по горизонтали
     * @param y Позиция по вертикали
     */
    public void drawCell(int x, int y) {
        System.out.println("***** SELECT *****");
        this.redraw(false);
    }

    /**
     * Нарисовать взрыв. Покажет действительное размещение всех мин и пустых клеток
     */
    public void drawBang() {
        System.out.println("***** BANG *****");
        this.redraw(true);
    }

    /**
     * Нарисовать поздравления при успешном прохождении
     */
    public void drawCongratulate() {
        System.out.println("***** CONGRATULATE *****");
    }

    /**
     * Вспомогательный метод. Перерисовывает все ячейки в System.out (консоль)
     * @param real True - рисовать действительное расположение мин и пустых клеток
     *             False - рисовать то, что думает пользователь
     */
    private void redraw(boolean real) {
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                cell.draw(System.out, real);
            }
            System.out.println();
        }
        System.out.println();
    }

}
