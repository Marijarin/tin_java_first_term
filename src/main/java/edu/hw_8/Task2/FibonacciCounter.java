package edu.hw_8.Task2;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Использование несколько потоков ускорит только рекурсивную версию.
 * Поэтому здесь приведен рекурсивный способ подсчет чисел Фибоначчи.
 * Насколько понимаю, быстрее считать последовательно в одном потоке.
 **/
public class FibonacciCounter {
    private final Map<Integer, BigInteger> memoize = new ConcurrentHashMap<>();
    private final int numberOfThreads;

    public FibonacciCounter(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
        memoize.put(0, BigInteger.ZERO);
        memoize.put(1, BigInteger.ONE);
    }

    BigInteger count(int n) {
        if (!memoize.containsKey(n)) {
            memoize.put(n, count(n - 1).add(count(n - 2)));
        }
        return memoize.get(n);
    }

    Runnable task(int n) {
        return (() -> {
            count(n);
        });
    }

    public BigInteger startCount(int n) throws InterruptedException {
        try (FixedThreadPool fixedThreadPool = new FixedThreadPool(numberOfThreads)) {
            fixedThreadPool.execute(task(n));
            return memoize.get(n);
        }
    }
}
