package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        String[] lines = new String[0];
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            while (br.ready()) {
                String line = br.readLine();
                if (line.startsWith("#") || line.equals("")) {
                    continue;
                }
                lines = line.split("=", 2);
                if (lines.length == 2 && (!lines[0].isBlank() && !lines[1].isBlank())) {
                    String key = lines[0];
                    String value = lines[1];
                    values.put(key, value);
                } else {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.get(key);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
    }
}
