package ru.job4j.ood.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoApp {
    Menu menu = new SimpleMenu();
    public static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        System.out.println("""
                enter menu item:
                to exit enter "exit"
                """);
        TodoApp todoApp = new TodoApp();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String line = br.readLine();
            while (!line.equals("exit")) {
                todoApp.menu.add(Menu.ROOT, line, STUB_ACTION);
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
