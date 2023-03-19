package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    private Map<FileProperty, List<Path>> mapOfFiles = new HashMap<>();
    private List<Path> paths = new ArrayList<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty properties = new FileProperty(file.toFile().length(), file.toFile().getName());
        List<Path> filePaths = new ArrayList<>();
        filePaths.add(file.toAbsolutePath());
        if (mapOfFiles.containsKey(properties)) {
            mapOfFiles.get(properties).addAll(filePaths);
            paths = mapOfFiles.get(properties);
        }
        mapOfFiles.put(properties, filePaths);
        return super.visitFile(file, attrs);
    }

    public List<Path> getPaths() {
        return paths;
    }
}
