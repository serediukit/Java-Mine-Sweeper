package main.java.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    private JPanel gamingPanel;
    private final int COLS = 30;
    private final int ROWS = 15;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        new MineSweeper();
    }

    private MineSweeper() {
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        gamingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Box box : Box.values()) {
                    g.drawImage((Image) box.image, box.ordinal() * IMAGE_SIZE, 0, this);
                }
            }
        };
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

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name) {
        String filename = "res/img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(filename);
        return icon.getImage();
    }
}
