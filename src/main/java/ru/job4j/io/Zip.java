package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path source : sources) {
                zip.putNextEntry(new ZipEntry(source.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void checkParams(ArgsName name) {
        File file = new File(name.get("d"));
        if (!file.isDirectory()) {
            throw new IllegalArgumentException(String.format("%s is not a directory", name.get("d")));
        }
        if (!name.get("e").endsWith(".bmp")) {
            throw new IllegalArgumentException("Enter exclusion extension");
        }
        if (!name.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("Not an archive");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsName = ArgsName.of(args);
        Zip zip = new Zip();
        checkParams(argsName);
        List<Path> files = Search.search(Path.of(argsName.get("d")),
                p -> !p.toFile().getName().endsWith(argsName.get("e")));
        zip.packFiles(files, new File(argsName.get("o")));
    }
}