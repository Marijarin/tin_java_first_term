package edu.hw_6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    DiskMap diskMap = new DiskMap();

    @Test
    void savesFileHere() throws IOException {
        Files.deleteIfExists(Path.of(diskMap.fileName));
        diskMap.inMemoryStorage.put("key1", "value1");
        diskMap.inMemoryStorage.put("key2", "value2");
        diskMap.writeToFile();
        String expected = "key1:value1";

        assertThat(Files.readAllLines(Path.of(diskMap.fileName)).get(0)).isEqualTo(expected);
    }

    @Test
    void readsFromFile() {
        diskMap.inMemoryStorage.put("key1", "value1");
        diskMap.inMemoryStorage.put("key2", "value2");
        diskMap.writeToFile();
        diskMap.clear();
        diskMap.readToRuntime();
        var expected = diskMap.inMemoryStorage.entrySet();

        assertThat(expected.size()).isEqualTo(2);
    }
}
