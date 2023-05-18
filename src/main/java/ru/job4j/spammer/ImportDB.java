package ru.job4j.spammer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ImportDB {
    private Properties cfg;
    private String dumb;

    public ImportDB(Properties cfg, String dumb) {
        this.cfg = cfg;
        this.dumb = dumb;
    }

    public List<User> load() {
        List<User> users = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(dumb))) {
            String s;
            while (reader.ready()) {
                s = reader.readLine();
                String[] line = s.split(";");
                if (line.length != 2 || "".equals(line[0]) || "".equals(line[1])) {
                    throw new IllegalArgumentException();
                } else {
                    users.add(new User(line[0], line[1]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return users;
    }

    public void save(List<User> users) throws ClassNotFoundException, SQLException {
        Class.forName(cfg.getProperty("jdbc.driver"));
        try (Connection connection =
                     DriverManager.getConnection(
                             cfg.getProperty("jdbc.url"),
                             cfg.getProperty("jdbc.username"),
                             cfg.getProperty("jdbc.password")
                     )) {
            for (User user : users) {
                try (PreparedStatement ps =
                             connection.prepareStatement("INSERT INTO users (name, email) VALUES (?, ?)")) {
                    ps.setString(1, user.name);
                    ps.setString(1, user.email);
                }
            }

        }
    }

    private static class User {
        String name;
        String email;

        public User(String name, String email) {
            this.name = name;
            this.email = email;
        }
    }

    public static void main(String[] args) throws Exception {
        Properties cfg = new Properties();
        try (InputStream in = ImportDB.class.getClassLoader().getResourceAsStream("app.properties")) {
            cfg.load(in);
        }
        ImportDB db = new ImportDB(cfg, "./dump.txt");
        db.save(db.load());
    }
}
