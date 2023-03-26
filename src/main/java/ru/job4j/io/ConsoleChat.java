package ru.job4j.io;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        int answersQnty = readPhrases().size();
        List<String> log = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        while (!OUT.equals(userInput)) {
            log.add(userInput);
            if (!STOP.equals(userInput)) {
                int answerNum = (int) (Math.random() * answersQnty);
                String answer = readPhrases().get(answerNum);
                System.out.println(answer);
                log.add(answer);
            } else {
                while (!CONTINUE.equals(userInput)) {
                    userInput = scanner.nextLine();
                    log.add(userInput);
                }
            }
            userInput = scanner.nextLine();
        }
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> rsl = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(botAnswers))) {
            while (br.ready()) {
                rsl.add(br.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pr = new PrintWriter(new FileWriter(path))) {
            log.forEach(pr::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("data\\logForChat.txt", "data\\botAnswers.txt");
        cc.run();
    }
}
