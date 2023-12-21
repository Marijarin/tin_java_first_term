package edu.hw_6;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {
    default AbstractFilter and(AbstractFilter filter) {
        return entry -> this.accept(entry) && filter.accept(entry);
    }

    static AbstractFilter isReadable() {
        return Files::isReadable;
    }

    static AbstractFilter isWritable() {
        return Files::isWritable;
    }

    static AbstractFilter globMatches(String s) {
        return entry -> {
            String fileName = entry.getFileName().toString();
            return fileName.endsWith(s);
        };
    }

    static AbstractFilter regexContains(Pattern regex) {
        return entry -> {
            String fileName = entry.getFileName().toString();
            Matcher matcher = regex.matcher(fileName);
            return matcher.find();
        };
    }

    static AbstractFilter isLargerThan(long yourSize) {
        return entry -> {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(
                entry,
                BasicFileAttributes.class
            );
            long size = basicFileAttributes.size();
            return size > yourSize;
        };
    }

    static AbstractFilter magicNumber(byte b) {
        return entry -> {
            byte[] all = Files.readAllBytes(entry);
            int size = Math.min(all.length, 512);
            for (int i = 0; i < size; i++) {
                if (all[i] == b) {
                    return true;
                }
            }
            return false;
        };
    }

    static AbstractFilter isRegular() {
        return entry -> {
            BasicFileAttributes basicFileAttributes = Files.readAttributes(
                entry,
                BasicFileAttributes.class
            );
            return basicFileAttributes.isRegularFile();
        };
    }
}
