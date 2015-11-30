package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.BaseAction;
import com.bikmop.minesweeper.BoardGenerator;
import com.bikmop.minesweeper.MinesweeperLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Реализация событий на графическом интерфейсе
 * ActionListener - нажатие на button
 * MouseListener - нажатие мыши на поле
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;

    public GUIAction(MinesweeperLogic logic, GUIBoard board, BoardGenerator generator) {
        super(logic, board, generator);
        this.board = board;
        this.board.addMouseListener(this);
    }


    public void actionPerformed(ActionEvent e) {
        this.initGame();
    }

    public void mouseClicked(MouseEvent e) {
        this.board.repaint();
    }

    public void mousePressed(MouseEvent e) {

    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {

    }
}
