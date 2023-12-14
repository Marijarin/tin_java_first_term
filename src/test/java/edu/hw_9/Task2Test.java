package edu.hw_9;

import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import edu.hw_9.task2.ManyFilesDirFinder;
import edu.hw_9.task2.PredicateFilesFinder;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void findsDirectoryNotLessThan5Files() {
        List<Path> results;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            results = forkJoinPool.invoke(new ManyFilesDirFinder(Path.of("src/test/java/edu/hw_9/test1"), 5));
        }

        assertThat(results.getFirst().toString()).contains("/hw_9/test1/3/9");
    }

    @Test
    void findsFilesMoreThan70Bytes() {
        List<Path> results;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            results = forkJoinPool.invoke(new PredicateFilesFinder(
                Path.of("src/test/java/edu/hw_9/test2"),
                (file -> file.isFile() && file.length() > 70)
            ));
        }

        assertThat(results.size()).isEqualTo(2);
    }

    @Test
    void findsPngFiles() {
        List<Path> results;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            results = forkJoinPool.invoke(new PredicateFilesFinder(
                Path.of("src/test/java/edu/hw_9/test2"),
                (file -> file.isFile() && file.getName().split("\\.")[1].equals("png"))
            ));
        }

        assertThat(results.getFirst().toString()).contains("src/test/java/edu/hw_9/test2/8/8/drawing.png");
    }
}
