package edu.hw_9;

import edu.hw_9.task1.Aggregator;
import edu.hw_9.task1.Answer;
import edu.hw_9.task1.CTask;
import edu.hw_9.task1.PTask;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.List;
import static edu.hw_9.task1.CTask.AVERAGE;
import static edu.hw_9.task1.CTask.MAX;
import static edu.hw_9.task1.CTask.MIN;
import static edu.hw_9.task1.CTask.SUM;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void countsSums() throws Exception {
        var pTasks = new PTask[] {
            new PTask(new double[] {1.0, 3.0, 3.0, 1.0}),
            new PTask(new double[] {0.9, 3.0, 3.0, 0.1})
        };

        var cTasks = new CTask[] {
            SUM,
            SUM
        };

        try (var aggregator = new Aggregator(pTasks, cTasks)) {
            aggregator.makeStats();
            Thread.sleep(10);

            var expected = List.of(
                new Answer(7.0, SUM),
                new Answer(8.0, SUM)
            );
            var result =
                aggregator.getStats().values().stream().sorted(Comparator.comparing(Answer::getAnswer)).toList();

            assertThat(result.toString()).isEqualTo(expected.toString());
        }
    }

    @Test
    void countsMaxs() throws Exception {
        var pTasks = new PTask[] {
            new PTask(new double[] {1.0, 3.0, 3.0, 1.0}),
            new PTask(new double[] {0.9, 3.0, 3.0, 0.1})
        };

        var cTasks = new CTask[] {
            MAX,
            MAX
        };

        try (var aggregator = new Aggregator(pTasks, cTasks)) {
            aggregator.makeStats();
            Thread.sleep(10);

            var expected = List.of(
                new Answer(3.0, MAX),
                new Answer(3.0, MAX)
            );
            var result =
                aggregator.getStats().values().stream().sorted(Comparator.comparing(Answer::getAnswer)).toList();

            assertThat(result.toString()).isEqualTo(expected.toString());
        }
    }

    @Test
    void countsMins() throws Exception {
        var pTasks = new PTask[] {
            new PTask(new double[] {1.0, 3.0, 3.0, 1.0}),
            new PTask(new double[] {0.9, 3.0, 3.0, 0.1})
        };

        var cTasks = new CTask[] {
            MIN,
            MIN
        };

        try (var aggregator = new Aggregator(pTasks, cTasks)) {
            aggregator.makeStats();
            Thread.sleep(10);

            var expected = List.of(
                new Answer(0.1, MIN),
                new Answer(1.0, MIN)
            );
            var result =
                aggregator.getStats().values().stream().sorted(Comparator.comparing(Answer::getAnswer)).toList();

            assertThat(result.toString()).isEqualTo(expected.toString());
        }
    }

    @Test
    void countsAverages() throws Exception {
        var pTasks = new PTask[] {
            new PTask(new double[] {1.0, 3.0, 3.0, 1.0}),
            new PTask(new double[] {0.9, 2.0, 3.0, 0.1})
        };

        var cTasks = new CTask[] {
            AVERAGE,
            AVERAGE
        };

        try (var aggregator = new Aggregator(pTasks, cTasks)) {
            aggregator.makeStats();
            Thread.sleep(10);

            var expected = List.of(
                new Answer(1.5, AVERAGE),
                new Answer(2.0, AVERAGE)
            );
            var result =
                aggregator.getStats().values().stream().sorted(Comparator.comparing(Answer::getAnswer)).toList();

            assertThat(result.toString()).isEqualTo(expected.toString());
        }
    }
}
