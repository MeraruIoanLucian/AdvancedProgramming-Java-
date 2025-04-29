package com.example.jdbc;

import java.io.InputStream;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) throws Exception {
        try (Connection conn = DbConnection.get()) {
            // 1. Run schema.sql
            InputStream in = Main.class.getClassLoader()
                    .getResourceAsStream("schema.sql");
            String sql = new String(in.readAllBytes());
            for (String stmt : sql.split(";")) {
                if (!stmt.isBlank()) conn.createStatement().execute(stmt);
            }

            // 2. Instantiate DAOs
            ContinentDao cDao = new ContinentDao(conn);
            CountryDao countryDao = new CountryDao(conn);

            // 3. Create and print
            Continent europe = cDao.create(new Continent("Europe"));
            System.out.println("Inserted continent: " + europe);

            Country france = countryDao.create(
                    new Country("France", "FRA", europe));
            System.out.println("Inserted country: " + france);

            // 4. Lookup
            System.out.println("Lookup continent ‘Europe’: "
                    + cDao.findByName("Europe"));
            System.out.println("Lookup country by ID: "
                    + countryDao.findById(france.getId()));
        }
    }
}