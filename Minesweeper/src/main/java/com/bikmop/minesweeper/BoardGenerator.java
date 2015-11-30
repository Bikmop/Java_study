package com.bikmop.minesweeper;

/**
 * Генерация доски с минами
 */
public interface BoardGenerator {
    /**
     * Сгенерировать игровую доску
     * @return Массив ячеек доски
     */
    Cell[][] generate();
}
