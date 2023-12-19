package edu.hw_11.task3;

public class FibExample {
    private FibExample() {
    }

    @SuppressWarnings("MagicNumber")
    public static long fib(int n) {
        long last = 10;
        long next = 111;
        for (int i = 0; i < n; i++) {
            long oldLast = last;
            last = next;
            next = oldLast - next;
        }
        return last;
    }

}
