package ru.job4j.io;

import jdk.dynalink.linker.LinkerServices;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private Predicate<Path> predicate;

    public SearchFiles(Predicate<Path> predicate) {
        this.predicate = predicate;
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        List<String> lines = Files.readAllLines(file);
        for (String line : lines) {
            if (predicate.test(file)) {
                getPaths(Path.of(line));
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public List<Path> getPaths(Path s) {
        List<Path> rsl = new ArrayList<>();
        rsl.add(s);
        return rsl;
    }
}
