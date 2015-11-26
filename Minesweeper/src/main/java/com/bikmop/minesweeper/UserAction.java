package com.bikmop.minesweeper;

/**
 * TODO: comment
 */
public interface UserAction {

    void initGame();

    void select(int x, int y, boolean bomb);

}
