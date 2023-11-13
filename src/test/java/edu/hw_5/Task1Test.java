package edu.hw_5;

import edu.hw_5.task1_task2.DateUtil;
import java.util.List;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    void averageIntervalOfStringDates() {
        List<String> intervals = List.of(
            "2022-03-12, 20:20 - 2022-03-12, 23:50",
            "2022-04-01, 21:30 - 2022-04-02, 01:20"
        );

        DateUtil d = new DateUtil();

        var result = d.showAverageTime(intervals);

        var correctOutput = "3ч 40м";

        assertThat(result).isEqualTo(correctOutput);
    }
}
