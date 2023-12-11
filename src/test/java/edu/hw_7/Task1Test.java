package edu.hw_7;

import org.junit.jupiter.api.Test;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void goodCounterWorks() throws InterruptedException {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(15100);
        int numberOfThreads = 15100;
        for (int j = 0; j < 5; j++) {
            try (ExecutorService service = Executors.newFixedThreadPool(15100)) {
                CountDownLatch latch = new CountDownLatch(numberOfThreads);
                for (int i = 0; i < numberOfThreads; i++) {
                    service.execute(() -> {
                        latch.countDown();
                        multiThreadCounter.incrementTo1();

                    });
                }
                latch.await();
            }
        }
        assertThat(numberOfThreads).isEqualTo(multiThreadCounter.thNumber);
    }

    @Test
    void badCounterWorks() throws InterruptedException {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(15100);
        int numberOfThreads = 15100;
        for (int j = 0; j < 5; j++) {
            try (ExecutorService service = Executors.newFixedThreadPool(15100)) {
                CountDownLatch latch = new CountDownLatch(numberOfThreads);
                for (int i = 0; i < numberOfThreads; i++) {
                    service.execute(() -> {
                        latch.countDown();
                        multiThreadCounter.badIncrementTo1();

                    });
                }
                latch.await();
            }
        }
        assertThat(numberOfThreads).isNotEqualTo(multiThreadCounter.unSafeCounter);
    }

    @Test
    void counterBaseFunctionWorks() {
        MultiThreadCounter multiThreadCounter = new MultiThreadCounter(15100);

        var result = multiThreadCounter.afterIncrement();

        assertThat(result).isEqualTo(15100);
    }
}
