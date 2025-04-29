package com.game;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Dictionary {
    private final Set<String> words;

    public Dictionary(String filename) throws IOException {
        words = new HashSet<>();
        InputStream in = getClass().getClassLoader().getResourceAsStream(filename);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + filename);
        }
        try (BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String line;
            while ((line = br.readLine()) != null) {
                String w = line.trim().toUpperCase();
                if (w.matches("^[A-Z]+$")) {
                    words.add(w);
                }
            }
        }
    }

    public String findWord(List<Tile> tiles) {
        int[] counts = new int[26];
        for (Tile t : tiles) {
            counts[t.getLetter() - 'A']++;
        }

        // stream the dictionary and pick the longest matching word
        return words.stream()
                .filter(w -> w.length() <= tiles.size())
                .filter(w -> {
                    int[] tmp = counts.clone();
                    for (char c : w.toCharArray()) {
                        if (--tmp[c - 'A'] < 0) return false;
                    }
                    return true;
                })
                .max(Comparator.comparingInt(String::length))
                .orElse(null);
    }
}
