package com.bikmop.minesweeper;

import com.bikmop.minesweeper.console.ConsoleBoard;
import com.bikmop.minesweeper.console.ConsoleCell;
import com.bikmop.minesweeper.logic.Easy;
import org.junit.Test;


public class BaseActionTest {

    final BaseAction action = new BaseAction(
            new Easy(), new ConsoleBoard(),
            new BoardGenerator() {
                public Cell[][] generate() {
                    return new Cell[][] {
                            {new ConsoleCell(true), new ConsoleCell(false)},
                            {new ConsoleCell(true), new ConsoleCell(false)}};
                }
            }
    );

    @Test
    public void successGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, true);
        action.select(0, 1, false);
        action.select(1, 1, false);
    }

    @Test
    public void failureGame() {
        action.initGame();
        action.select(0, 0, true);
        action.select(1, 0, false);
    }

}