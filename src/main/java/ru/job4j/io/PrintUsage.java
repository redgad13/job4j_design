package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new FileOutputStream("data/print.txt"));
             PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("SomeThing");
            stream.write("new thing".getBytes());
            writer.println("some thing");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
