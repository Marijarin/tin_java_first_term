package edu.hw_6;

import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void canWriteToFile() throws IOException {
        QuotationsWriter quotationsWriter = new QuotationsWriter();
        Path path = Path.of("quotation.txt");
        Files.deleteIfExists(path);
        String cite = "Testing leads to failure, and failure leads to understanding. - Burt Rutan";
        quotationsWriter.writeToFile(cite);

        assertThat(Files.exists(path)).isTrue();
    }

    @Test
    void writesThisString() throws IOException {
        QuotationsWriter quotationsWriter = new QuotationsWriter();
        Path path = Path.of("quotation.txt");
        Files.deleteIfExists(path);
        String cite = "Testing leads to failure, and failure leads to understanding. - Burt Rutan";
        quotationsWriter.writeToFile(cite);

        var result = Files.readAllLines(path).get(0);

        assertThat(result).isEqualTo(cite);
    }
}
