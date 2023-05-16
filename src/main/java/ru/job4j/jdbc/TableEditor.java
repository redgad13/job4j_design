package ru.job4j.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        String url = properties.getProperty("hibernate.connection.url");
        String username = properties.getProperty("hibernate.connection.username");
        String password = properties.getProperty("hibernate.connection.password");
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createTable(String tableName) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("CREATE TABLE IF NOT EXISTS %s(%s, %s);",
                    tableName, "id SERIAL PRIMARY KEY", "name_t text");
            statement.execute(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropTable(String tableName) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("DROP TABLE IF EXISTS %s", tableName);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addColumn(String tableName, String columnName, String type) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("ALTER TABLE %s ADD COLUMN %s %s", tableName, columnName, type);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropColumn(String tableName, String columnName) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("ALTER TABLE %s DROP COLUMN %s", tableName, columnName);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        try {
            Statement statement = connection.createStatement();
            String sql = String.format("ALTER TABLE %s RENAME %s TO %s", tableName, columnName, newColumnName);
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public String getTableScheme(String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "SELECT * FROM %s LIMIT 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        try (InputStream in = Class.forName("org.postgresql.Driver")
                .getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        try (TableEditor editor = new TableEditor(config)) {
            editor.createTable("new_table");
            System.out.println(editor.getTableScheme("new_table"));
            System.out.println("***************************************************");
            editor.addColumn("new_table", "new_column", "VARCHAR (15)");
            System.out.println(editor.getTableScheme("new_table"));
            System.out.println("***************************************************");
            editor.dropColumn("new_table", "new_column");
            System.out.println(editor.getTableScheme("new_table"));
            System.out.println("***************************************************");
            editor.renameColumn("new_table", "name_t", "just_name");
            System.out.println(editor.getTableScheme("new_table"));
            editor.dropTable("new_table");
        }
    }
}