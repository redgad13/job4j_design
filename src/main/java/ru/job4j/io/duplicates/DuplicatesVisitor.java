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
    List<Path> paths;

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        FileProperty properties = new FileProperty(file.toFile().length(), file.toFile().getName());
        paths = new ArrayList<>();
        paths.add(file.toAbsolutePath());
        if (mapOfFiles.containsKey(properties)) {
            mapOfFiles.get(properties).addAll(paths);
            for (Path path : mapOfFiles.get(properties)) {
                System.out.println(path.toAbsolutePath());
            }
        }
        mapOfFiles.put(properties, paths);
        return super.visitFile(file, attrs);
    }
}
