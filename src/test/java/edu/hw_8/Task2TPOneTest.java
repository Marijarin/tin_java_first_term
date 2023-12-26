package edu.hw_8;

import edu.hw_8.Task2.FibonacciCounter;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2TPOneTest {
    @Test
    void countTaskCountsCorrectly() {
        int n = 5;
        FibonacciCounter fibonacciCounter = new FibonacciCounter(1);
        fibonacciCounter.count(5);

        var result = fibonacciCounter.memoize.get(n);

        assertThat(result.intValue()).isEqualTo(n);
    }

    @Test
    void cuntInMultiThreadWorksCorrect() throws InterruptedException {
        int n = 5;
        FibonacciCounter fibonacciCounter = new FibonacciCounter(n);
        fibonacciCounter.startCount(n);

        var result = fibonacciCounter.memoize.get(n);

        assertThat(result.intValue()).isEqualTo(n);
    }
}
