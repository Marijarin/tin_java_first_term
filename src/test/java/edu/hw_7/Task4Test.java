package edu.hw_7;

import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    void countPiSingleThread() {
        PiCounter piCounter = new PiCounter(1000000, 1);

        var result = piCounter.countPi();

        assertThat(result).isCloseTo(3.14, Percentage.withPercentage(3));
    }

    @Test
    void countPiManyThreads() {
        PiCounter piCounter = new PiCounter(1000000, 15100);

        var result = piCounter.countPi();

        assertThat(result).isCloseTo(3.14, Percentage.withPercentage(3));
    }

    @Test
    void piThreadStats() {
        PiCounterStats piCounterStats = new PiCounterStats();

        piCounterStats.makeThreadsStats();

        var result = piCounterStats.timeInS;

        assertThat(result[0]).isGreaterThan(result[3]);
    }
}
