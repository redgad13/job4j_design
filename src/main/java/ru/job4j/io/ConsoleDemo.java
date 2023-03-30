package ru.job4j.io;

import java.io.Console;
import java.util.Arrays;

public class ConsoleDemo {
    public static void main(String[] args) {
        String login;
        char[] charPassword;
        Console console = System.console();
        if (console == null) {
            System.out.println("Консоль недоступна");
            return;
        }
        login = console.readLine("Введите логин: ");
        console.printf("Ваш логин: %s\n", login);
        charPassword = console.readPassword("Введите пароль: ");
        System.out.println("Ваш пароль: " + String.valueOf(charPassword));
        Arrays.fill(charPassword, ' ');
    }

}
