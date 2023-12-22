package edu.hw_6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import static edu.hw_6.AbstractFilter.globMatches;
import static edu.hw_6.AbstractFilter.isLargerThan;
import static edu.hw_6.AbstractFilter.isReadable;
import static edu.hw_6.AbstractFilter.isRegular;
import static edu.hw_6.AbstractFilter.isWritable;
import static edu.hw_6.AbstractFilter.magicNumber;
import static edu.hw_6.AbstractFilter.regexContains;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void findsReadable() throws IOException {
        AbstractFilter filter = isReadable();
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsWritable() throws IOException {
        AbstractFilter filter = isWritable();
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsMatch() throws IOException {
        AbstractFilter filter = globMatches("java");
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsWithRegex() throws IOException {
        AbstractFilter filter = regexContains(Pattern.compile("\\d*"));
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsLarger() throws IOException {
        AbstractFilter filter = isLargerThan(800L);
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsMagicNumber() throws IOException {
        AbstractFilter filter = magicNumber((byte) 'C');
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task1Test.java"));
    }

    @Test
    void findsRegular() throws IOException {
        AbstractFilter filter = isRegular();
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }

    @Test
    void findsWithChainFilter() throws IOException {
        AbstractFilter filter = isReadable().and(isLargerThan(800L));
        Path basePath = Paths.get("src/test/java/edu/hw_6");
        var result = new ArrayList<Path>();
        try (DirectoryStream<Path> pathList = Files.newDirectoryStream(basePath, filter)) {
            for (Path path : pathList) {
                result.add(path);
            }
        }

        assertThat(result).asList().contains(Paths.get("src/test/java/edu/hw_6/Task3Test.java"));
    }
}
