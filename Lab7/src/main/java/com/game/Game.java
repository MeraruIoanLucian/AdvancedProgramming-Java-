package com.game;
import com.game.Timekeeper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public class Game {
    public static void main(String[] args) throws IOException, InterruptedException {
        Bag bag = new Bag();
        Board board = new Board();
        Dictionary dict = new Dictionary("dictionary.txt");

        AtomicInteger currentTurn = new AtomicInteger(0);
        AtomicBoolean gameOver = new AtomicBoolean(false);
        Object turnLock = new Object();

        // time limit in seconds
        Timekeeper tk = new Timekeeper(60, gameOver, turnLock);
        tk.start();

        int numPlayers = 3;
        List<Player> players = new ArrayList<>();
        players.add(new Player(0, numPlayers, "Ionut", bag, board, dict, currentTurn, gameOver, turnLock));
        players.add(new Player(1, numPlayers, "Bogdan",   bag, board, dict, currentTurn, gameOver, turnLock));
        players.add(new Player(2, numPlayers, "Andrei", bag, board, dict, currentTurn, gameOver, turnLock));

        for (Player p : players) p.start();
        for (Player p : players) p.join();

        board.printSubmissions();

        String winner = players.stream()
            .max((a, b) -> Integer.compare(a.getScore(), b.getScore()))
            .map(Thread::getName).orElse("None");
        System.out.println("Winner: " + winner);
    }
}