package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        boolean offline;
        boolean online;
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pr = new PrintWriter(new FileOutputStream(target))
        ) {
            List<String> log = new ArrayList<>();
            while (br.ready()) {
                String line = br.readLine();
                log.add(line);
            }
            for (int i = 1; i < log.size() - 1; i++) {
                String[] currStats = log.get(i).split(" ");
                String[] nextStats = log.get(i + 1).split(" ");

                offline = (log.get(i - 1).startsWith("200") || log.get(i - 1).startsWith("300"))
                        && (log.get(i).startsWith("400") || log.get(i).startsWith("500"));
                online = (log.get(i).startsWith("400") || log.get(i).startsWith("500"))
                        && (log.get(i + 1).startsWith("200") || log.get(i + 1).startsWith("300"));
                if (offline) {
                    pr.write((currStats[1] + ";"));
                }
                if (online) {
                    pr.write(nextStats[1]);
                    pr.write(System.lineSeparator());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}
