package edu.hw_8.Task2.another_one;

import edu.hw_8.Task2.FibonacciCounter;

@SuppressWarnings("RegexpSinglelineJava")
public class FibTask implements Runnable {
    final int n;
    FibonacciCounter fibonacciCounter = new FibonacciCounter(1);

    public FibTask(int n) {
        this.n = n;
    }

    @Override public void run() {
        fibonacciCounter.count(n);
        System.out.println(fibonacciCounter.memoize.get(n));
    }
}
