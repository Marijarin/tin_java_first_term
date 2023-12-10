package edu.hw_6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    DiskMap diskMap = new DiskMap();

    @Test
    void hasCopy() throws IOException {
        Files.deleteIfExists(Path.of(diskMap.fileName));
        diskMap.inMemoryStorage.put("key1", "value1");
        diskMap.inMemoryStorage.put("key2", "value2");
        diskMap.writeToFile();

        diskMap.cloneFile(Path.of(diskMap.fileName));

        assertThat(Files.exists(Path.of(System.getenv("HOME") + "/diskMap - копия.txt"))).isTrue();
    }
}
