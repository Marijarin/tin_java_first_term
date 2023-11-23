package edu.hw_7;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiThreadCounter {
    AtomicInteger counter = new AtomicInteger(0);
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

    void incrementTo1(){
        counter.addAndGet(1);
    }

    void badIncrementTo1(){
        unSafeCounter++;
    }
    int afterIncrement(){
        addThreads();
        for (Thread t: threads) {
            t.start();
        }
        try {
            for (Thread t: threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return counter.get();
    }
}
