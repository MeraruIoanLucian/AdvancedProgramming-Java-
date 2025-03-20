package org.example;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        Location[] locations = new Location[] {
                new Location("Dust", LocationType.FRIENDLY),
                new Location("Inferno", LocationType.ENEMY),
                new Location("Mirage", LocationType.NEUTRAL),
                new Location("Nuke", LocationType.FRIENDLY),
                new Location("Ancient", LocationType.ENEMY),
                new Location("Train", LocationType.NEUTRAL),
                new Location("Cache", LocationType.FRIENDLY)
        };

        TreeSet<Location> friendlyLocations = Stream.of(locations)
                .filter(loc -> loc.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));

        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Stream.of(locations)
                .filter(loc -> loc.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType)
                        .thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));

        enemyLocations.forEach(System.out::println);
    }
}