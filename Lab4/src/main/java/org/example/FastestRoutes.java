package org.example;

import org.jgrapht.Graph;
import org.jgrapht.alg.shortestpath.DijkstraShortestPath;
import org.jgrapht.graph.DefaultDirectedWeightedGraph;
import org.jgrapht.graph.DefaultWeightedEdge;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class FastestRoutes {
    private Graph<Location, DefaultWeightedEdge> graph;
    private Map<Location, Double> shortestTimes;


    public FastestRoutes(Location[] locations) {
        graph = new DefaultDirectedWeightedGraph<>(DefaultWeightedEdge.class);
        for (Location loc : locations) {
            graph.addVertex(loc);
        }
        Random rand = new Random();
        for (int i = 0; i < locations.length; i++) {
            for (int j = 0; j < locations.length; j++) {
                if (i != j) {
                    DefaultWeightedEdge edge = graph.addEdge(locations[i], locations[j]);
                    if (edge != null) {
                        double weight = 5 + rand.nextDouble() * 15;
                        graph.setEdgeWeight(edge, weight);
                    }
                }
            }
        }
    }

    public void computeFastestTimes(Location start) {
        shortestTimes = new HashMap<>();
        DijkstraShortestPath<Location, DefaultWeightedEdge> dijkstra =
                new DijkstraShortestPath<>(graph);
        for (Location loc : graph.vertexSet()) {
            double time = dijkstra.getPathWeight(start, loc);
            shortestTimes.put(loc, time);
        }
    }

    public Map<Location, Double> getShortestTimes() {
        return shortestTimes;
    }
}