package com.bikmop.minesweeper.gui;

import com.bikmop.minesweeper.Cell;
import com.bikmop.minesweeper.BoardGenerator;
import com.bikmop.minesweeper.logic.Easy;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * TODO: comment
 */
public class Main {
    private static final JPanel controlPanel = new JPanel();
    private static final GUIBoard board = new GUIBoard();
//    private static boolean g

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                final JFrame frame = new JFrame();
                frame.setTitle("Сапер");
                frame.setLayout(new BorderLayout());
                frame.setSize(500, 500);
                frame.add(board, BorderLayout.CENTER);
                board.setBorder(new EmptyBorder(10, 10, 10, 10));
                frame.add(controlPanel, BorderLayout.PAGE_END);
                controlPanel.setLayout(new FlowLayout());
                final JButton generate = new JButton("Начать");
                generate.addActionListener(
                        new GUIAction(
                                new Easy(), board,
                                new BoardGenerator() {
                                    public Cell[][] generate() {
                                        return new Cell[][] {{new GUICell(true), new GUICell(false)},
                                                             {new GUICell(true), new GUICell(false)}};
                                    }
                                }
                        )
                );
                controlPanel.add(generate);
                centre(frame);
                frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                frame.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        closePerform(frame);
                    }
                });
                frame.setVisible(true);
            }
        });
    }

    public static void centre(Window w) {
        Dimension us = w.getSize();
        Dimension them = Toolkit.getDefaultToolkit().getScreenSize();
        int newX = (them.width - us.width) / 2;
        int newY = (them.height - us.height) / 2;
        w.setLocation(newX, newY);
    }

    public static void closePerform(JFrame frame) {
        frame.setVisible(false);
        frame.dispose();
        System.exit(0);
    }
}
