package com.game;
import com.game.Dictionary;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Represents a player thread in the word game with turn-taking and dictionary logic.
 */
public class Player extends Thread {
    private final int id;
    private final int totalPlayers;
    private final Bag bag;
    private final Board board;
    private final Dictionary dictionary;
    private final AtomicInteger currentTurn;
    private final AtomicBoolean gameOver;
    private final Object turnLock;
    private int score;

    public Player(int id, int totalPlayers, String name,
                  Bag bag, Board board, Dictionary dictionary,
                  AtomicInteger currentTurn, AtomicBoolean gameOver, Object turnLock) {
        super(name);
        this.id = id;
        this.totalPlayers = totalPlayers;
        this.bag = bag;
        this.board = board;
        this.dictionary = dictionary;
        this.currentTurn = currentTurn;
        this.gameOver = gameOver;
        this.turnLock = turnLock;
        this.score = 0;
    }

    @Override
    public void run() {
        while (!gameOver.get() && !bag.isEmpty()) {
            synchronized (turnLock) {
                while (!gameOver.get() && currentTurn.get() != id) {
                    try {
                        turnLock.wait();
                    } catch (InterruptedException ignored) { }
                }
                if (gameOver.get()) break;

                // Draw 7 tiles
                List<Tile> hand = bag.extractTiles(7);
                if (hand == null || hand.isEmpty()) {
                    gameOver.set(true);
                } else {
                    // Find a valid word
                    String word = dictionary.findWord(hand);
                    if (word != null) {
                        int points = 0;
                        for (char c : word.toCharArray()) {
                            for (Tile t : hand) {
                                if (t.getLetter() == c) {
                                    points += t.getPoints();
                                    break;
                                }
                            }
                        }
                        board.submitWord(word, points, getName());
                        score += points;
                        // draw k more tiles
                        List<Tile> extra = bag.extractTiles(word.length());
                        if (extra == null || extra.isEmpty()) {
                            gameOver.set(true);
                        }
                    }
                }

                // next turn
                currentTurn.set((id + 1) % totalPlayers);
                turnLock.notifyAll();
            }
        }
        System.out.println(getName() + " final score: " + score);
    }

    public int getScore() {
        return score;
    }
}