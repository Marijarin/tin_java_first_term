package edu.hw_8.Task2;

import edu.hw_8.Task2.thread_pool_one.FixedThreadPool;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FibonacciCounter {
    public final Map<Integer, BigInteger> memoize = new ConcurrentHashMap<>();
    private final int numberOfThreads;

    public FibonacciCounter(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        memoize.put(0, BigInteger.ZERO);
        memoize.put(1, BigInteger.ONE);
    }

    public BigInteger count(int n) {
        memoize.computeIfAbsent(n, integer -> count(n - 1).add(count(n - 2)));
        return memoize.get(n);
    }

    Runnable task(int n) {
        return (() -> count(n));
    }

    public BigInteger startCount(int n) throws InterruptedException {
        try (FixedThreadPool fixedThreadPool = new FixedThreadPool(numberOfThreads)) {
            fixedThreadPool.execute(task(n));
            fixedThreadPool.close();
            return memoize.get(n);
        }
    }
}
