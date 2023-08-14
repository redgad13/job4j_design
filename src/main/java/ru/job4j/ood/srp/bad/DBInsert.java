package ru.job4j.ood.srp.bad;

import java.sql.*;
import java.util.*;

public class DBInsert implements DBOperation {
    private Connection connection;

    public DBInsert() throws ClassNotFoundException, SQLException {
        initConnection();
    }

    public void initConnection() throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        String url = "jdbc:postgresql://localhost:5432/my_db";
        String login = "postgres";
        String password = "password";
        connection = DriverManager.getConnection(url, login, password);
    }

    @Override
    public void insert(Map<String, String> data) {
        try (PreparedStatement statement = connection.prepareStatement("INSERT INTO table(keys, values) values (?,?)")) {
            statement.setString(1, "firstKey");
            statement.setString(2, "firstValue");
            statement.setString(1, "secondKey");
            statement.setString(2, "secondValue");
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Map<String, String>> get(String key) {
        List<java.util.Map<String, String>> rsl = new ArrayList<>();
        Map<String, String> keyValueMap = new HashMap<>();
        try (PreparedStatement statement = connection.prepareStatement("SELECT key_column, value_column FROM table")) {
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                String keyFromSet = set.getString("key_column");
                String value = set.getString("value_column");
                keyValueMap.put(keyFromSet, value);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        for (Map.Entry<String, String> stringStringEntry : keyValueMap.entrySet()) {
            rsl.add(keyValueMap);
        }
        return rsl;
    }
}
