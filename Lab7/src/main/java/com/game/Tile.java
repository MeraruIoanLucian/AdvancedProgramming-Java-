package com.game;
import java.io.Serializable;


public class Tile implements Serializable {
    private char letter;
    private int points;

    public Tile(char letter, int points) {
        this.letter = letter;
        this.points = points;
    }

    public char getLetter() {
        return letter;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public String toString() {
        return letter + "(" + points + ")";
    }
}