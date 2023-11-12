package edu.hw_5.task_3;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonDateParser extends DateParser {
    @Override
    public Optional<LocalDate> check(String toCheck) {
        if (toCheck.contains("-") || toCheck.contains("/")){
            return process(toCheck);
        } else {
            return checkNext(toCheck);
        }
    }

    @Override
    public Optional<LocalDate> process(String toCheck) {
        Pattern commonDate1 = Pattern.compile("(?<year>\\d{4})-(?<month>\\d{1,2})-(?<day>\\d{1,2})");
        Pattern commonDate2 = Pattern.compile("(?<day>\\d{1,2})/(?<month>\\d{1,2})/(?<year>\\d{2,4})");
        Matcher matcher1 = commonDate1.matcher(toCheck);
        Matcher matcher2 = commonDate2.matcher(toCheck);
        if (matcher1.find()) {
            return parseFromRegExp(matcher1);
        } else if (matcher2.find()) {
            return parseFromRegExp(matcher2);
        }
        return Optional.empty();
    }

    private Optional<LocalDate> parseFromRegExp(Matcher matcher) {
        try {
            String yearS = matcher.group("year");
            if (yearS.length() == 2) {
                yearS = "20" + yearS;
            }
            int year = Integer.parseInt(yearS);
            int month = Integer.parseInt(matcher.group("month"));
            int day = Integer.parseInt(matcher.group("day"));
            return Optional.of(LocalDate.of(year, month, day));
        } catch (RuntimeException e) {
            return Optional.empty();
        }
    }
}
