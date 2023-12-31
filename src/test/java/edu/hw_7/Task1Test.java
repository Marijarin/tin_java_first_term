package edu.hw_7;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void goodCounterWorks() throws InterruptedException {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(100);
        int numberOfThreads = 100;
        for (int j = 0; j < 5; j++) {
            try (ExecutorService service = Executors.newFixedThreadPool(100)) {
                for (int i = 0; i < numberOfThreads; i++) {
                    service.execute(multiThreadCounter::incrementTo1);
                }
            }
        }
        assertThat(numberOfThreads).isEqualTo(multiThreadCounter.thNumber);
    }

    @Test
    void badCounterWorks() throws InterruptedException {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(100);
        int numberOfThreads = 100;
        for (int j = 0; j < 5; j++) {
            try (ExecutorService service = Executors.newFixedThreadPool(100)) {
                for (int i = 0; i < numberOfThreads; i++) {
                    service.execute(multiThreadCounter::badIncrementTo1);
                }
            }
        }
        assertThat(numberOfThreads).isNotEqualTo(multiThreadCounter.unSafeCounter);
    }

    @Test
    void counterBaseFunctionWorks() {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(100);

        var result = multiThreadCounter.afterIncrement();

        assertThat(result).isEqualTo(100);
    }
}
