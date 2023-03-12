package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader br = new BufferedReader(new FileReader(source));
             PrintWriter pr = new PrintWriter(new FileOutputStream(target))
        ) {
            boolean isOn = true;
            while (br.ready()) {
                String currLine = br.readLine();
                boolean currLineIsOn = currLine.startsWith("200") || currLine.startsWith("300");
                if (isOn != currLineIsOn) {
                    String[] stats = currLine.split(" ");
                    pr.write(stats[1] + ";");
                    if (!isOn) {
                        pr.write(System.lineSeparator());
                    }
                    isOn = !isOn;
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
