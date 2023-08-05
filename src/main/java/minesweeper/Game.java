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
        if (!gameOver()) {
            openBox(coord);
            checkWinner();
        }
    }

    private void checkWinner() {
        if (state == GameState.PLAYED) {
            if (flag.getCountOfClosedBoxes() == bomb.getTotalBombs()) {
                state = GameState.WINNER;
            }
        }
    }

    private void openBox(Coord coord) {
        switch (flag.get(coord)) {
            case OPENED -> {
                setOpenedToClosedBoxesAroundNumber(coord);
            }
            case FLAGED -> {
                return;
            }
            case CLOSED -> {
                switch (bomb.get(coord)) {
                    case ZERO -> openBoxesAround(coord);
                    case BOMB -> openBombs(coord);
                    default -> flag.setOpenedToBox(coord);
                }
            }
        }
    }

    private void openBombs(Coord bombed) {
        state = GameState.BOMBED;
        flag.setBombedToBox(bombed);
        for (Coord coord : Ranges.getListCoords()) {
            if (bomb.get(coord) == Box.BOMB) {
                flag.setOpenedToClosedBombBox(coord);
            } else {
                flag.setNoBombFlagedSafeBox(coord);
            }
        }
    }

    private void openBoxesAround(Coord coord) {
        flag.setOpenedToBox(coord);
        for (Coord around : Ranges.getCoordsAround(coord)) {
            openBox(around);
            checkWinner();
        }
    }

    public void setOpenedToClosedBoxesAroundNumber(Coord coord) {
        if (bomb.get(coord) != Box.BOMB)
            if (flag.getCountOfFlagedBoxesAround(coord) == bomb.get(coord).getNumber())
                for (Coord around : Ranges.getCoordsAround(coord))
                    if (flag.get(around) == Box.CLOSED)
                        openBox(around);
    }

    public void pressRightButton(Coord coord) {
        if (!gameOver())
            flag.toggleFlagedToBox(coord);
    }

    private boolean gameOver() {
        return state != GameState.PLAYED;
    }
}
