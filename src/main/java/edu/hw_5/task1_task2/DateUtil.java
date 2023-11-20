package edu.hw_5.task1_task2;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class DateUtil {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd, HH:mm");
    public final List<String> intervals = List.of(
        "2022-03-12, 20:20 - 2022-03-12, 23:50",
        "2022-04-01, 21:30 - 2022-04-02, 01:20",
        "2022-02-11, 20:20 - 2022-02-11, 23:50",
        "2022-05-02, 21:30 - 2022-05-03, 01:20",
        "2022-01-23, 20:20 - 2022-01-23, 23:50",
        "2022-10-24, 21:30 - 2022-10-25, 01:20"
    );

    //#1
    public String showAverageTime(List<String> intervals) {
        double minutes = averageTimeSpent(intervals);
        return convertAverageMinutesToString(minutes);
    }

    private String convertAverageMinutesToString(double minutes) {
        Duration d = Duration.ofMinutes((long) minutes);
        String hd = d.toHoursPart() + "ч ";
        String md = d.toMinutesPart() + "м";
        return hd + md;
    }

    private Double averageTimeSpent(List<String> intervals) {
        return intervals.stream().map(interval -> {
                Pair p = new Pair(interval.split(" - ")[0], interval.split(" - ")[1]);
                return spentTimeDuration(p);
            }
        ).collect(Collectors.averagingLong(Long::longValue));
    }

    private Long spentTimeDuration(Pair p) {
        LocalDateTime startLocalDateTime = LocalDateTime.parse(p.first, formatter);
        LocalDateTime endLocalDateTime = LocalDateTime.parse(p.second, formatter);
        return Duration.between(startLocalDateTime, endLocalDateTime).toMinutes();
    }

    //#2
    public List<LocalDate> fridays13(int year) {
        return makeDates13Day(year)
            .stream()
            .filter(it -> getDayNumber(it) == DateConstants.FRIDAY.valueOf()
                && it.getDayOfMonth() == DateConstants.THIS_DAY.valueOf())
            .toList();
    }

    private int getDayNumber(LocalDate date) {
        DayOfWeek day = date.getDayOfWeek();
        return day.getValue();
    }

    private List<LocalDate> makeDates13Day(int year) {
        List<LocalDate> ldList = new ArrayList<>();
        for (int i = 1; i <= DateConstants.MONTHS_COUNT.valueOf(); i++) {
            LocalDate ld = LocalDate.of(year, i, DateConstants.THIS_DAY.valueOf());
            ldList.add(ld);
        }
        return ldList;
    }

    //Next Friday 13

    public LocalDate nextFriday13(LocalDate localDate) {
        LocalDate next13 = goTo13(localDate);
        return findFriday13(next13.getYear(), next13.getMonthValue());
    }

    private LocalDate findFriday13(int year, int month) {
        for (int i = month; i <= DateConstants.MONTHS_COUNT.valueOf(); i++) {
            LocalDate ld = LocalDate.of(year, i, DateConstants.THIS_DAY.valueOf());
            if (getDayNumber(ld) == DateConstants.FRIDAY.valueOf()) {
                return ld;
            }
        }
        return findFriday13(year + 1, month);
    }

    private LocalDate goTo13(LocalDate localDate) {
        if (localDate.getDayOfMonth() > DateConstants.THIS_DAY.valueOf()) {
            return localDate.with(TemporalAdjusters.firstDayOfNextMonth()).withDayOfMonth(13);
        } else {
            return localDate.withDayOfMonth(DateConstants.THIS_DAY.valueOf());
        }
    }

}
