package com.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameModel implements Serializable {
    private static final long serialVersionUID = 1L;
    private List<Dot> dots;
    private List<Line> lines;
    private int currentPlayer; // 0 for Blue, 1 for Red

    public GameModel() {
        dots = new ArrayList<>();
        lines = new ArrayList<>();
        currentPlayer = 0;
    }

    public List<Dot> getDots() { return dots; }
    public List<Line> getLines() { return lines; }
    public int getCurrentPlayer() { return currentPlayer; }
    public void switchPlayer() { currentPlayer = 1 - currentPlayer; }

    public void addDot(Dot dot) { dots.add(dot); }
    public void addLine(Line line) { lines.add(line); }
}