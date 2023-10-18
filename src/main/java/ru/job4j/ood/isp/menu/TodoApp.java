package ru.job4j.ood.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoApp {

    Menu menu = new SimpleMenu();
    private static final ActionDelegate STUB_ACTION = System.out::println;
    private static String line;

    public static void main(String[] args) {
        message();
        TodoApp todoApp = new TodoApp();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            line = br.readLine();
            while (!line.equals("exit")) {
                todoApp.addParent(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Menu.MenuItemInfo menu : todoApp.menu) {
            System.out.println(menu.getNumber() + menu.getName());
        }

    }

    private void addParent(String child) {
        menu.add(Menu.ROOT, child, STUB_ACTION);
        System.out.println("would you like to add child item? yes/no");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String answer = br.readLine();
            while ("yes".equals(answer)) {
                addChild(child);
                System.out.println("would you like to add more child item? yes/no");
                answer = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addChild(String parent) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("enter child name");
            String child = br.readLine();
            menu.add(parent, child, STUB_ACTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void message() {
        System.out.println("""
                enter menu item:
                to exit enter "exit"
                """);
    }

}
