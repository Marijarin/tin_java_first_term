package edu.hw_11.task3;

public class Fib {
    public static long fib(int n) {
        long last = 0;
        long next = 1;
        for (int i = 0; i < n; i++) {
            long oldLast = last;
            last = next;
            next = oldLast + next;
        }
        return last;
    }
}
