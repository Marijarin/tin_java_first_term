package edu.hw_10.task2;

public class FibCalculator implements Calculator {
    @Override
    @Cache(persist = true)
    public long fib(int n) {
        long last = 0L;
        long next = 1L;
        for (int i = 0; i < n; i++) {
            long oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }

    @Override
    @Cache(persist = false)
    public long fi(int n) {
        long last = 0L;
        long next = 1L;
        for (int i = 0; i < n; i++) {
            long oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
