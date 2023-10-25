package ru.job4j.ood.isp.menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TodoApp {

    Menu menu = new SimpleMenu();
    private static final ActionDelegate STUB_ACTION = System.out::println;

    public static void main(String[] args) {
        message();
        TodoApp todoApp = new TodoApp();
        try (BufferedReader mainReader = new BufferedReader(new InputStreamReader(System.in))) {
            String line = mainReader.readLine();
            while (!line.equals("выход")) {
                todoApp.addParent(line, mainReader);
                message();
                line = mainReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Menu.MenuItemInfo menu : todoApp.menu) {
            System.out.println(menu.getNumber() + menu.getName());
        }

    }

    private void addParent(String child, BufferedReader mainReader) {
        menu.add(Menu.ROOT, child, STUB_ACTION);
        System.out.println("добавить подпункт меню? да/нет");
        try {
            String answer = mainReader.readLine();
            while ("да".equals(answer)) {
                addChild(child, mainReader);
                System.out.println("добавить еще подпункт да/нет");
                answer = mainReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addChild(String parent, BufferedReader mainReader) throws IOException {
        try  {
            System.out.println("введите название подпункта");
            String child = mainReader.readLine();
            menu.add(parent, child, STUB_ACTION);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void message() {
        System.out.println("""
                Введите элемент меню:
                Для выхода введите "выход"
                """);
    }

}
