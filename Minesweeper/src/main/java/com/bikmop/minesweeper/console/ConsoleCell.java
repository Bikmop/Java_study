package com.bikmop.minesweeper.console;

import com.bikmop.minesweeper.Cell;

import java.io.PrintStream;

/**
 * Консольная реализация ячейки
 */
public class ConsoleCell implements Cell<PrintStream> {
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
    public ConsoleCell(boolean bomb) {
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

    /**
     * Отрисовка ячейки
     * @param paint Вспомогательный класс для отрисовки
     * @param real Рисовать то, что есть или предположение пользователя
     */
    public void draw(PrintStream paint, boolean real) {
        if (real) {
            if (this.isBomb()) {
                paint.print("[*] ");    // Мина
            } else {
                paint.print("[ ] ");    // Пустая
            }
        } else {
            if (this.suggestBomb) {
                paint.print("[?] ");    // Пометить миной
            } else if (this.suggestEmpty) {
                paint.print("[ ] ");    // Открыть
            } else {
                paint.print("[X] ");    // Картинка по умолчанию
            }
        }
    }

    public void draw(PrintStream paint, int x, int y, int size, boolean real) {}
}
