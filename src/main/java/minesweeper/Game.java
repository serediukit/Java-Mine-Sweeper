package main.java.minesweeper;

public class Game {
    private Bomb bomb;
    private Flag flag;
    private GameState state;

    public Game(int cols, int rows, int bombs) {
        Ranges.setSize(new Coord(cols, rows));
        bomb = new Bomb(bombs);
        flag = new Flag();
    }

    public void start() {
        bomb.start();
        flag.start();
        state = GameState.PLAYED;
    }

    public GameState getState() {
        return state;
    }

    public Box getBox(Coord coord) {
        if (Box.OPENED == flag.get(coord))
            return bomb.get(coord);
        return flag.get(coord);
    }

    public void pressLeftButton(Coord coord) {
        flag.setOpenedToBox(coord);
    }

    public void pressRightButton(Coord coord) {
        flag.toggleFlagedToBox(coord);
    }
}
