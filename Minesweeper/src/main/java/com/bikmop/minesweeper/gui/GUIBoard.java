package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.Board;
import com.bikmop.minesweeper.Cell;

import javax.swing.*;
import java.awt.*;

/**
 * Графическая (swing) реализация игральной доски
 */
public class GUIBoard extends JPanel implements Board {
    /** Ширина ячейки */
    public static final int PADDING = 50;

    public Cell<Graphics>[][] cells;

    /**
     * Метод отрисовки (swing) содержимого ячеек
     * @param graphics
     */
    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        if (this.cells != null) {
            for (int x = 0; x != cells.length; x++) {
                for (int y = 0; y != cells[0].length; y++) {
                    cells[x][y].draw(graphics, x*PADDING, y*PADDING, PADDING, false);
                    graphics.setColor(Color.black);
                    graphics.drawRect(x * PADDING, y * PADDING, PADDING, PADDING);
                }
            }
        }
    }

    public void drawBoard(Cell[][] cells) {
        this.cells = cells;
        this.repaint();
    }

    public void drawCell(int x, int y) {
        this.repaint();
    }

    public void drawBang() {
        this.repaint();
    }

    public void drawCongratulate() {

    }
}
