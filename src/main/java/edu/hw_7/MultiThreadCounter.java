package edu.hw_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

public class MultiThreadCounter {
    LongAdder counter = new LongAdder();
    List<Thread> threads = new ArrayList<>();
    public final int thNumber;

    public int unSafeCounter = 0;

    MultiThreadCounter(int thNumber) {
        this.thNumber = thNumber;
    }

    private void addThreads() {
        for (int i = 0; i < thNumber; i++) {
            threads.add(new Thread(this::incrementTo1));
        }
    }

    void incrementTo1() {
        counter.increment();
    }

    void badIncrementTo1() {
        unSafeCounter++;
    }

    long afterIncrement() {
        addThreads();
        for (Thread t : threads) {
            t.start();
        }
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return counter.sumThenReset();
    }
}
