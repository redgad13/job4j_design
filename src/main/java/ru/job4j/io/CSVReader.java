package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        List<String[]> list = new ArrayList<>();
        String[] dataFromLine;
        String[] dataFromFilter;
        try (Scanner scanner = new Scanner(new File(argsName.get("path")));
             PrintWriter pr = new PrintWriter(argsName.get("out"))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                dataFromLine = line.split(argsName.get("delimiter"));
                list.add(dataFromLine);
            }
            dataFromFilter = argsName.get("filter").split(",");
            List<String> allFields = Arrays.stream(list.get(0)).toList();
            List<Integer> indexes = findIndexes(allFields, dataFromFilter);
            StringBuilder builder = new StringBuilder();
            for (String[] strings : list) {
                if (argsName.get("out").equals("stdout")) {
                    for (Integer index : indexes) {
                        printData(argsName, strings[index], builder);
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    System.out.println(builder);
                    builder.setLength(0);
                } else {
                    for (Integer index : indexes) {
                        writeDataToFile(argsName, strings[index], builder);
                    }
                    builder.deleteCharAt(builder.length() - 1);
                    pr.write(String.valueOf(builder));
                    builder.setLength(0);
                    pr.println();
                }
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }


    private static void printData(ArgsName argsName, String s, StringBuilder builder) {
        builder.append(s).append(argsName.get("delimiter"));
    }

    private static void writeDataToFile(ArgsName argsName, String s, StringBuilder builder) {
        builder.append(s).append(argsName.get("delimiter"));
    }

    private static List<Integer> findIndexes(List<String> allFields, String[] dataFromFilter) {
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
