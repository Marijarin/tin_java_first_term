package edu.hw_6;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.zip.CRC32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.Checksum;

public class QuotationsWriter {
    String quotation = "Programming is learned by writing programs. ― Brian Kernighan";

    void writeToFile(String s) throws IOException {
        var file = Files.createFile(Path.of("quotation.txt")).toFile();
        Checksum checksum = new CRC32();
        try (OutputStream fileOutputStream = new FileOutputStream(file);
             CheckedOutputStream checkedOutputStream = new CheckedOutputStream(fileOutputStream, checksum);
             BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(checkedOutputStream);
             OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                 bufferedOutputStream,
                 StandardCharsets.UTF_8
             )) {
            PrintWriter printWriter = new PrintWriter(outputStreamWriter, true);
            printWriter.write(s);
        }
    }
}
