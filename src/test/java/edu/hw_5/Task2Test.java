package edu.hw_5;

import edu.hw_5.task1_task2.DateUtil;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void getAllFridays13InAYear() {
        int year = 2024;

        DateUtil dateUtil = new DateUtil();

        var result = dateUtil.fridays13(year);

        var correctOutput = List.of(LocalDate.of(2024, 9, 13), LocalDate.of(2024, 12, 13));

        assertThat(result).isEqualTo(correctOutput);
    }

    @Test
    void closestFriday13() {
        var previous = LocalDate.of(2024, 12, 14);

        DateUtil d = new DateUtil();

        var result = d.nextFriday13(previous);

        var correctOutput = LocalDate.of(2025, 6, 13);

        assertThat(result).isEqualTo(correctOutput);
    }

    @Test
    void closestFriday13IsThis() {
        var previous = LocalDate.of(2024, 12, 13);

        DateUtil d = new DateUtil();

        var result = d.nextFriday13(previous);

        var correctOutput = LocalDate.of(2024, 12, 13);

        assertThat(result).isEqualTo(correctOutput);
    }
}
