package main.java.minesweeper;

import javax.swing.*;
import java.awt.*;

public class MineSweeper extends JFrame {
    private Game game;
    private JPanel gamingPanel;
    private final int COLS = 30;
    private final int ROWS = 15;
    private final int BOMBS = 100;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        System.out.println();
        new MineSweeper();
    }

    private MineSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initPanel();
        initFrame();
    }

    private void initPanel() {
        gamingPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getListCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE, this);
                }
            }
        };
        gamingPanel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(gamingPanel);
    }

    private void initFrame() {
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
        setIconImage(getImage("logo"));
    };

    private void setImages() {
        for (Box box : Box.values()) {
            box.image = getImage(box.name());
        }
    }

    private Image getImage(String name) {
        String filename = "/img/" + name.toLowerCase() + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));
        return icon.getImage();
    }
}
