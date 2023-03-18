package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashSet;
import java.util.Set;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
    Set<FileProperty> filePropertySet = new HashSet<>();

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        boolean isOk = filePropertySet.add(new FileProperty(file.toFile().length(),
                file.toFile().getName(), Path.of(file.toFile().getPath())));
        if (!isOk) {
            System.out.println(file.toAbsolutePath());
            FileProperty rsl = filePropertySet.stream()
                    .filter(f -> f.getSize() == file.toFile().length()
                            && f.getName().equals(file.toFile().getName()))
                    .findFirst()
                    .get();
            System.out.println(rsl.getPath());
        }

        return super.visitFile(file, attrs);
    }
}
