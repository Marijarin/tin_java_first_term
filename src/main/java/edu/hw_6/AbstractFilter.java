package edu.hw_6;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AbstractFilter extends DirectoryStream.Filter<Path> {

    static AbstractFilter isReadable() {
        return Files::isReadable;
    }

    static AbstractFilter isWritable() {
        return Files::isWritable;
    }

    static AbstractFilter globMatches(Pattern regex) {
        return entry -> {
            Pattern p = Pattern.compile("\\.\\w+$");
            Matcher m1 = p.matcher((CharSequence) entry);
            String extension = "";
            if (m1.matches()) {
                extension = m1.group();
            }
            Matcher m2 = regex.matcher(extension);
            return m2.matches();
        };
    }

    static AbstractFilter regexContains(Pattern regex) {
        return entry -> {
            String fileName = entry.getFileName().toString();
            Matcher matcher = regex.matcher(fileName);
            return matcher.matches();
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
