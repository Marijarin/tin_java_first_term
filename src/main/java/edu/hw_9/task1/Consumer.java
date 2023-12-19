package edu.hw_9.task1;

import java.util.Arrays;
import java.util.concurrent.BlockingQueue;
import java.util.function.Function;

public class Consumer implements Runnable {
    final BlockingQueue<Integer> queue;
    final CTask cTask;

    public Consumer(BlockingQueue<Integer> queue, CTask cTask) {
        this.queue = queue;
        this.cTask = cTask;
    }

    @Override
    public void run() {

    }

    private double sum(double[] data) {
        return Arrays.stream(data).sum();
    }

    private double average(double[] data) {
        return Arrays.stream(data).average().orElse(0.0);
    }

    private double max(double[] data) {
        return Arrays.stream(data).max().orElse(0.0);
    }

    private double min(double[] data) {
        return Arrays.stream(data).min().orElse(0.0);
    }

    private double makeOperation(double[] data) {
        switch (this.cTask) {
            case SUM -> {
                return sum(data);
            }
            case MAX -> {
                return max(data);
            }
            case MIN -> {
                return min(data);
            }
            case AVERAGE -> {
                return average(data);
            }
            default -> {
                return 0.0;
            }
        }
    }
}
