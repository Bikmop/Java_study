package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.Cell;

import java.awt.*;

/**
 * Графическая реализация ячейки
 * Graphics - тип-параметр для отрисовки
 */
public class GUICell implements Cell<Graphics> {
    /** Отступ от края ячейки равен 1/INDENT */
    private final static int INDENT = 5;

    /** Является ли ячейка миной */
    private boolean bomb;
    /** Является ли ячейка миной по предположению пользователя */
    private boolean suggestBomb = false;
    /** Является ли ячейка пустой по предположению пользователя */
    private boolean suggestEmpty = false;

    /**
     * Конструктор
     * @param bomb Является ли миной
     */
    public GUICell(boolean bomb) {
        this.bomb = bomb;
    }


    /**
     * Является ли миной на самом деле
     * @return Мина или нет
     */
    public boolean isBomb() {
        return this.bomb;
    }

    /**
     * Пользователь предполагает, что мина
     * @return Предположительно мина
     */
    public boolean isSuggestBomb() {
        return this.suggestBomb;
    }

    /**
     * Пользователь предполагает, что пустая
     * @return Предположительно пустая
     */
    public boolean isSuggestEmpty() {
        return this.suggestEmpty;
    }

    /**
     * Установить значение "пустая"
     */
    public void suggestEmpty() {
        this.suggestEmpty = true;
    }

    /**
     * Установить значение "мина"
     */
    public void suggestBomb() {
        this.suggestBomb = true;
    }


    public void draw(Graphics paint, boolean real) {
    }

    public void draw(Graphics paint, int coordinateX, int coordinateY, int cellSize, boolean real) {
//        paint. - имеет много методов для разных отрисовок - фигуры, картинки и т.п.

        int pixelIndent = cellSize/INDENT;
        int pixelSize = cellSize - (2*cellSize)/INDENT;
        if (real) {
            if (this.isBomb()) {
                paint.setColor(new Color(200, 0, 0));
                paint.fillOval(coordinateX + pixelIndent, coordinateY + pixelIndent,
                        pixelSize, pixelSize);   // Мина
                paint.setColor(new Color(255, 0, 0));
                paint.drawOval(coordinateX + pixelIndent, coordinateY + pixelIndent,
                        pixelSize, pixelSize);   // Мина
            } else {
                paint.setColor(new Color(230, 230, 230));
                paint.fillRect(coordinateX + 1, coordinateY + 1,
                        cellSize - 1, cellSize - 1);    // Пустая
            }
        } else {
            if (this.suggestBomb) {
                paint.setColor(new Color(0, 100, 200));
                paint.fillOval(coordinateX + pixelIndent, coordinateY + pixelIndent,
                        pixelSize, pixelSize);
                paint.setColor(new Color(0, 0, 255));
                paint.drawOval(coordinateX + pixelIndent, coordinateY + pixelIndent,
                        pixelSize, pixelSize);  // Пометить миной
            } else if (this.suggestEmpty) {
                paint.setColor(new Color(230, 230, 230));
                paint.fillRect(coordinateX + 1, coordinateY + 1,
                        cellSize - 1, cellSize - 1);      // Открыть
            } else {
                paint.setColor(Color.lightGray);
                paint.fillRect(coordinateX + pixelIndent, coordinateY + pixelIndent,
                        pixelSize, pixelSize);    // Картинка по умолчанию



            }
        }
    }
}
