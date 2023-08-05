package main.java.minesweeper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MineSweeper extends JFrame {
    private Game game;
    private JPanel gamingPanel;
    private JLabel label;
    private final int COLS = 30;
    private final int ROWS = 20;
    private final int BOMBS = 150;
    private final int IMAGE_SIZE = 50;

    public static void main(String[] args) {
        System.out.println();
        new MineSweeper();
    }

    private MineSweeper() {
        game = new Game(COLS, ROWS, BOMBS);
        game.start();
        setImages();
        initLabel();
        initPanel();
        initFrame();
    }

    private void initLabel() {
        label = new JLabel("Вітаю на Херсонщині!");
        add(label, BorderLayout.SOUTH);
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

        gamingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);
                if (e.getButton() == MouseEvent.BUTTON1)
                    game.pressLeftButton(coord);
                if (e.getButton() == MouseEvent.BUTTON2)
                    game.start();
                if (e.getButton() == MouseEvent.BUTTON3)
                    game.pressRightButton(coord);
                label.setText(getMessage());
                gamingPanel.repaint();
            }
        });

        gamingPanel.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        add(gamingPanel);
    }

    private String getMessage() {
        return switch (game.getState()) {
            case PLAYED -> "Розміновуй Україну!";
            case BOMBED -> "Ти здох)";
            case WINNER -> "Крим НАШ!";
        };
    }

    private void initFrame() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setTitle("Mine Sweeper");
        setResizable(false);
        setVisible(true);
        pack();
        setLocationRelativeTo(null);
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
