package com.game;
import java.util.ArrayList;
import java.util.List;

public class Board {
    private List<String> submissions;

    public Board() {
        submissions = new ArrayList<>();
    }

    public synchronized void submitWord(String word, int score, String playerName) {
        String entry = playerName + ": \"" + word + "\" (" + score + " points)";
        submissions.add(entry);
        System.out.println("Board: " + entry);
    }

    public synchronized void printSubmissions() {
        System.out.println("\nFinal Board Submissions:");
        for (String s : submissions) {
            System.out.println(s);
        }
    }
}