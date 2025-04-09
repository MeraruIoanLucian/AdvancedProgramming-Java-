package com.game;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bag {
    private List<Tile> tiles;

    public Bag() {
        tiles = new ArrayList<>();
        for (char letter = 'A'; letter <= 'Z'; letter++) {
            for (int i = 0; i < 10; i++) {
                int points = 1 + (int)(Math.random() * 10);
                tiles.add(new Tile(letter, points));
            }
        }
        Collections.shuffle(tiles);
    }

    public synchronized List<Tile> extractTiles(int count) {
        if (tiles.isEmpty())
            return null;
        List<Tile> hand = new ArrayList<>();
        for (int i = 0; i < count && !tiles.isEmpty(); i++) {
            hand.add(tiles.remove(0));
        }
        return hand;
    }

    public synchronized boolean isEmpty() {
        return tiles.isEmpty();
    }
}