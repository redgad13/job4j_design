package ru.job4j.ioexam;

import ru.job4j.io.ArgsName;
import ru.job4j.io.SearchFiles;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FindFiles {
    public static void main(String[] args) throws IOException {
        if (args.length != 4) {
            throw new IllegalArgumentException("Check the quantity of parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        checkParams(argsName);
        Path directory = Path.of(argsName.get("d"));
        String outputFile = argsName.get("o");
        List<Path> lines = findFiles(directory, p -> p.getFileName().toString().contains(argsName.get("n")));
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(outputFile))) {
            for (Path line : lines) {
                bufferedWriter.write(line.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkParams(ArgsName argsName) throws IOException {
        File dir = new File(argsName.get("d"));
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not a directory", argsName.get("d")));
        }
        if ("mask".equals(argsName.get("t"))) {
            /*вот тут не понимаю что делать */
        } else if ("name".equals(argsName.get("t"))) {
            /*вот тут не понимаю что делать */
        } else if ("regex".equals(argsName.get("t"))) {
            /*вот тут не понимаю что делать */
        }
        File outputFile = new File(argsName.get("o"));
        if (!outputFile.exists()) {
            outputFile.createNewFile();
        }
    }

    public static List<Path> findFiles(Path start, Predicate<Path> predicate) throws IOException {
        SearchFiles searchFiles = new SearchFiles(predicate);
        Files.walkFileTree(start, searchFiles);
        return searchFiles.getPaths();
    }
}
