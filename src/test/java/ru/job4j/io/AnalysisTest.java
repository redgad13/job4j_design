package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {
    @Test
    void unavailable(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("testSource.txt").toFile();
        File target = tempDir.resolve("testTarget.txt").toFile();
        try (PrintWriter writer = new PrintWriter(source)) {
            writer.write("""
                    200 10:56:01
                    500 10:57:01
                    400 10:58:01
                    500 10:59:01
                    400 11:01:02
                    200 11:02:02""");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.getAbsolutePath(), target.getAbsolutePath());
        StringBuilder rsl = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(target))) {
            br.lines().forEach(rsl::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(rsl.toString());
    }
}