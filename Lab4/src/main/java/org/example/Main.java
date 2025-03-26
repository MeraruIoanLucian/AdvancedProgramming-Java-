package org.example;

import com.github.javafaker.Faker;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        Location[] locations = new Location[10];
        LocationType[] types = LocationType.values();
        Random rand = new Random();
        for (int i = 0; i < locations.length; i++) {
            String name = faker.address().cityName();
            LocationType type = types[rand.nextInt(types.length)];
            locations[i] = new Location(name, type);
        }

        TreeSet<Location> friendlyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.FRIENDLY)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("Friendly Locations:");
        friendlyLocations.forEach(System.out::println);

        LinkedList<Location> enemyLocations = Arrays.stream(locations)
                .filter(loc -> loc.getType() == LocationType.ENEMY)
                .sorted(Comparator.comparing(Location::getType).thenComparing(Location::getName))
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("\nEnemy Locations:");
        enemyLocations.forEach(System.out::println);


        Map<LocationType, List<Location>> locationsByType = Arrays.stream(locations)
                .collect(Collectors.groupingBy(Location::getType));
        locationsByType.forEach((type, list) -> System.out.println(type + ": " + list));

        Location start = locations[0];
        FastestRoutes fastestRoutes = new FastestRoutes(locations);
        fastestRoutes.computeFastestTimes(start);
        Map<Location, Double> shortestTimes = fastestRoutes.getShortestTimes();

        System.out.println("\nFastest travel times from " + start.getName() + ":");
        for (LocationType type : new LocationType[]{LocationType.FRIENDLY, LocationType.NEUTRAL, LocationType.ENEMY}) {
            System.out.println(type + " locations:");
            List<Location> locs = locationsByType.getOrDefault(type, new ArrayList<>());
            locs.stream()
                    .sorted(Comparator.comparing(loc -> shortestTimes.getOrDefault(loc, Double.POSITIVE_INFINITY)))
                    .forEach(loc -> System.out.println(loc.getName() + " - " +
                            shortestTimes.getOrDefault(loc, Double.POSITIVE_INFINITY) + " minutes"));
        }
    }
}