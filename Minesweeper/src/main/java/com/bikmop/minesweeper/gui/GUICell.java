package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.Cell;

import java.awt.*;

/**
 * TODO: comment
 */
public class GUICell implements Cell<Graphics> {

    public boolean isBomb() {
        return false;
    }

    public boolean isSuggestBomb() {
        return false;
    }

    public boolean isSuggestEmpty() {
        return false;
    }

    public void suggestEmpty() {

    }

    public void suggestBomb() {

    }

    public void draw(Graphics paint, boolean real) {

    }
}
