package edu.hw_5.task_3;

import java.time.LocalDate;
import java.util.Optional;

public abstract class DateParser {
    public DateParser next;

    public abstract Optional<LocalDate> check(String toCheck);

    public abstract Optional<LocalDate> process(String toCheck);

    public Optional<LocalDate> checkNext(String toCheck) {
        if (next == null) {
            return Optional.empty();
        }
        return next.check(toCheck);
    }

    public static DateParser link(DateParser first, DateParser... chain) {
        DateParser head = first;
        for (DateParser nextInChain : chain) {
            head.next = nextInChain;
            head = nextInChain;
        }
        return first;
    }
}
