package com.game;

import java.util.List;

/**
 * Implements a simple version of Prim's algorithm to compute the minimum spanning tree weight.
 * The MST weight is used here as the optimal score.
 */
public class MSTCalculator {
    public static double computeMSTWeight(List<Dot> dots) {
        if (dots.isEmpty()) return 0;
        int n = dots.size();
        boolean[] inTree = new boolean[n];
        double[] minDist = new double[n];
        for (int i = 0; i < n; i++) {
            minDist[i] = Double.POSITIVE_INFINITY;
        }
        minDist[0] = 0;
        double total = 0;
        for (int i = 0; i < n; i++) {
            int u = -1;
            for (int j = 0; j < n; j++) {
                if (!inTree[j] && (u == -1 || minDist[j] < minDist[u])) {
                    u = j;
                }
            }
            if (u == -1) break;
            inTree[u] = true;
            total += minDist[u];
            for (int v = 0; v < n; v++) {
                if (!inTree[v]) {
                    double d = dots.get(u).distance(dots.get(v));
                    if (d < minDist[v]) {
                        minDist[v] = d;
                    }
                }
            }
        }
        return total;
    }
}