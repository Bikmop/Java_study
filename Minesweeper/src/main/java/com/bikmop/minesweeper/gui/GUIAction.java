package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.BaseAction;
import com.bikmop.minesweeper.Board;
import com.bikmop.minesweeper.GeneratorBoard;
import com.bikmop.minesweeper.SaperLogic;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * TODO: comment
 */
public class GUIAction extends BaseAction implements ActionListener, MouseListener {
    private GUIBoard board;

    public GUIAction(SaperLogic logic, GUIBoard board, GeneratorBoard generator) {
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
