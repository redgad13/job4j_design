package ru.job4j.jdbc;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DebugDemo {
    private Connection con;

    public DebugDemo() throws Exception {
        initConnection();
    }

    private void initConnection() throws Exception {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/idea_db";
        String login = "postgres";
        String password = "password";
        con = DriverManager.getConnection(url, login, password);
    }

    public void createTable() {
        try (Statement statement = con.createStatement()) {
            statement.execute(
                    "CREATE TABLE IF NOT EXISTS cities (id serial primary key, name text, population int);");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public City insert(City city) {
        try (PreparedStatement statement = con.prepareStatement(
                "INSERT INTO cities (name, population) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS
        )) {
            statement.setString(1, city.getName());
            statement.setInt(2, city.getPopulation());
            statement.execute();
            try (ResultSet set = statement.getGeneratedKeys()) {
                if (set.next()) {
                    city.setId(set.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return city;
    }

    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        try (PreparedStatement statement = con.prepareStatement("SELECT * FROM cities;");
             ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                cities.add(new City(
                        set.getInt("id"),
                        set.getString("name"),
                        set.getInt("population")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    public static void main(String[] args) throws Exception {
        City first = new City("CityOne", 100);
        City second = new City("CityTwo", 200);
        DebugDemo debugDemo = new DebugDemo();
        debugDemo.createTable();
        debugDemo.insert(first);
        debugDemo.insert(second);
        for (City city : debugDemo.findAll()) {
            System.out.println("city = " + city);
        }
    }
}
