package ru.job4j.io;

import java.io.File;

public class Dir {
    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("Root folder is null. Usage  ROOT_FOLDER.");
        }
        File file = new File(args[0]);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("Directory %s doesn't exist", file.getAbsoluteFile()));
        }
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not a directory", file.getAbsoluteFile()));
        }

        System.out.println(String.format("size : %s", file.getTotalSpace()));
        for (File listFile : file.listFiles()) {
            System.out.println(String.format("directory \"%s\" size is %d", listFile.getName(), listFile.length()));
        }
    }
}
