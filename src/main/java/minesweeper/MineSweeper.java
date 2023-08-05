package main.java.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    private JPanel gamingPanel;
    private final int COLS = 40;
    private final int ROWS = 25;
    private final int IMAGE_SIZE = 30;

    public static void main(String[] args) {
        new MineSweeper();
    }

    private MineSweeper() {
        initPanel();
        initFrame();
    }

    private void initPanel() {
        gamingPanel = new JPanel();
        gamingPanel.setPreferredSize(new Dimension(COLS * IMAGE_SIZE, ROWS * IMAGE_SIZE));
        add(gamingPanel);
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    };
}
