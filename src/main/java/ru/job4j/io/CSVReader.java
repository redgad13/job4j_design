package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String[]> list = new ArrayList<>();
        String[] dataFromLine;
        List<String> dataFromFilter;
        try (Scanner scanner = new Scanner(new File(argsName.get("path")));
             PrintWriter pr = new PrintWriter(argsName.get("out"))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                dataFromLine = line.split("; ");
                list.add(dataFromLine);
            }
            dataFromFilter = List.of(argsName.get("filter").split(","));
            List<String> allFields = List.of(list.get(0));
            List<Integer> indexes = findIndexes(allFields, dataFromFilter);
            for (String[] strings : list) {
                for (Integer index : indexes) {
                    doAction(argsName, strings[index], pr);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doAction(ArgsName argsName, String s, PrintWriter pr) {
        if (argsName.get("out").equals("stdout")) {
            System.out.print(s + " ");
        } else {
            pr.write(s + " ");
        }
    }

    private static List<Integer> findIndexes(List<String> allFields, List<String> dataFromFilter) {
        List<Integer> indexes = new ArrayList<>();
        int index;
        for (String field : dataFromFilter) {
            index = allFields.indexOf(field);
            indexes.add(index);
        }
        return indexes;
    }

    private static void checkParams(ArgsName name) {
        File file = new File(name.get("path"));
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("%s is not a file", name.get("path")));
        }
        Pattern p = Pattern.compile(";");
        Matcher delimiter = p.matcher(name.get("delimiter"));
        if (!delimiter.matches()) {
            throw new IllegalArgumentException("Enter correct delimiter");
        }
        p = Pattern.compile("\\w+");
        Matcher output = p.matcher(name.get("out"));
        if (!output.find()) {
            throw new IllegalArgumentException("Enter output data");
        }
        p = Pattern.compile("\\w+,");
        Matcher columns = p.matcher(name.get("filter"));
        if (!columns.find()) {
            throw new IllegalArgumentException("Enter filter rules");
        }
    }

    public static void main(String[] args) {
        if (args.length != 4) {
            throw new IllegalArgumentException("Check the quantity of parameters");
        }
        ArgsName argsName = ArgsName.of(args);
        checkParams(argsName);
        handle(argsName);
    }
}
