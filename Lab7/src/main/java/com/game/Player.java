package com.game;

import java.util.List;


public class Player extends Thread {
    private Bag bag;
    private Board board;
    private int score;

    public Player(String name, Bag bag, Board board) {
        super(name);
        this.bag = bag;
        this.board = board;
        this.score = 0;
    }

    @Override
    public void run() {
        while (true) {
            List<Tile> hand = bag.extractTiles(7);
            if (hand == null || hand.isEmpty()) {
                break;
            }
            StringBuilder sb = new StringBuilder();
            int wordScore = 0;
            for (Tile tile : hand) {
                sb.append(tile.getLetter());
                wordScore += tile.getPoints();
            }
            String word = sb.toString();
            board.submitWord(word, wordScore, getName());
            score += wordScore;
            int needed = word.length();
            List<Tile> newTiles = bag.extractTiles(needed);
            if (newTiles == null || newTiles.isEmpty()) {
                break;
            }
        }
        System.out.println(getName() + " finished with score: " + score);
    }

    public int getScore() {
        return score;
    }
}