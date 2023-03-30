package ru.job4j.io;

import java.io.*;
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
        String delimiter = argsName.get("delimiter");
        try (Scanner scanner = new Scanner(new File(argsName.get("path")))) {
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                dataFromLine = line.split(delimiter);
                list.add(dataFromLine);
            }
            dataFromFilter = argsName.get("filter").split(",");
            List<String> allFields = Arrays.stream(list.get(0)).toList();
            List<Integer> indexes = findIndexes(allFields, dataFromFilter);
            StringBuilder builder = new StringBuilder();
            for (String[] strings : list) {
                for (Integer index : indexes) {
                    builder.append(strings[index]).append(delimiter);
                }
                builder.deleteCharAt(builder.length() - 1);
                builder.append(System.lineSeparator());
            }
            doAction(argsName, builder);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void doAction(ArgsName argsName, StringBuilder builder) {
        if (!("stdout").equals(argsName.get("out"))) {
            try (FileWriter fr = new FileWriter(argsName.get("out"), true)) {
                fr.write(String.valueOf(builder));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.print(builder);
        }
        builder.setLength(0);
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
