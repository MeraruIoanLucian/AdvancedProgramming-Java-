package com.game;
import java.util.ArrayList;
import java.util.List;

public class Game {
    public static void main(String[] args) {
        Bag bag = new Bag();
        Board board = new Board();
        List<Player> players = new ArrayList<>();

        players.add(new Player("Alice", bag, board));
        players.add(new Player("Bob", bag, board));
        players.add(new Player("Carol", bag, board));

        for (Player player : players) {
            player.start();
        }
        for (Player player : players) {
            try {
                player.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        board.printSubmissions();

        int highest = -1;
        String winner = "";
        for (Player player : players) {
            if (player.getScore() > highest) {
                highest = player.getScore();
                winner = player.getName();
            }
        }
        System.out.println("\nGame Over! The winner is " + winner + " with " + highest + " points.");
    }
}