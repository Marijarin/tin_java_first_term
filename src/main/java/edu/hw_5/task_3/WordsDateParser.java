package edu.hw_5.task_3;

import java.time.LocalDate;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordsDateParser extends DateParser {
    @Override
    public Optional<LocalDate> check(String toCheck) {
        Pattern digit = Pattern.compile("[0-9]");
        Matcher hasLetter = digit.matcher(toCheck);
        if (!hasLetter.find()) {
            return process(toCheck);
        } else {
            return checkNext(toCheck);
        }
    }

    @Override
    public Optional<LocalDate> process(String toCheck) {
        switch (toCheck.trim().toLowerCase()) {
            case "today" -> {
                return Optional.of(LocalDate.now());
            }
            case "tomorrow" -> {
                return Optional.of(LocalDate.now().plusDays(1));
            }
            case "yesterday" -> {
                return Optional.of(LocalDate.now().minusDays(1));
            }
            default -> {
                return Optional.empty();
            }
        }
    }
}
