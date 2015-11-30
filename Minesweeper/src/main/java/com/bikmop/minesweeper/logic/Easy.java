package com.bikmop.minesweeper.logic;


import com.bikmop.minesweeper.Cell;
import com.bikmop.minesweeper.MinesweeperLogic;

/**
 * Реализация простой логики игры
 */
public class Easy implements MinesweeperLogic {
    /** Поле с ячейками */
    private Cell[][] cells;

    /**
     * Загрузка массива ячеек в логику
     * @param cells Ячейки
     */
    public void loadBoard(Cell[][] cells) {
        this.cells = cells;
    }

    /**
     * Взрываемся, если выбрали действие отличное от пометить как мину
     * @param x Координата Х
     * @param y Координата У
     * @return Нужно ли взрываться
     */
    public boolean shouldBang(int x, int y) {
        final Cell selected = this.cells[x][y];
        return selected.isBomb() && !selected.isSuggestBomb();
    }

    /**
     * TODO: не уверен в правильности метода!!!
     * @return
     */
    public boolean finish() {
        boolean finish = false;
        for (Cell[] row : cells) {
            for (Cell cell : row) {
                finish = ((cell.isSuggestBomb() && cell.isBomb()) ||
                          (cell.isSuggestEmpty() && !cell.isBomb()));
            }
        }
        return finish;
    }

    /**
     * Отмечаем ячейку в зависимости от предположения пользователя
     * @param x Координата Х
     * @param y Координата У
     * @param bomb Мина или пусто
     */
    public void suggest(int x, int y, boolean bomb) {
        if (bomb) {
            this.cells[x][y].suggestBomb();
        } else {
            this.cells[x][y].suggestEmpty();
        }
    }
}
