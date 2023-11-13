package edu.hw_5.task_3;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class AgoDateParser extends DateParser {
    @Override
    public Optional<LocalDate> check(String toCheck) {

        if (toCheck.contains("ago")) {
            return process(toCheck);
        } else {
            return checkNext(toCheck);
        }
    }

    @Override
    public Optional<LocalDate> process(String toCheck) {
        List<String> parseList = List.of(toCheck.split(" "));
        if (parseList.get(1).equals("day") || parseList.get(1).equals("days")) {
            try {
                int days = Integer.parseInt(parseList.get(0));
                return Optional.of(LocalDate.now().minusDays(days));
            } catch (RuntimeException e) {
                return Optional.empty();
            }
        }
        return Optional.empty();
    }
}
