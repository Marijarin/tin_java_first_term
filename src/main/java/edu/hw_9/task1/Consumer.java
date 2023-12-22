package edu.hw_9.task1;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

@SuppressWarnings("ReturnCount")
public class Consumer implements Runnable {
    final BlockingQueue<PTask> queue;
    final CTask cTask;
    final Map<PTask, Answer> stats;

    public Consumer(Map<PTask, Answer> stats, BlockingQueue<PTask> queue, CTask cTask) {
        this.stats = stats;
        this.queue = queue;
        this.cTask = cTask;

    }

    @Override
    public void run() {
        try {
            if (!queue.isEmpty()) {
                var data = queue.poll();
                stats.put(data, new Answer(makeOperation(data.data), this.cTask));
            }
        } catch (Exception ex) {
            System.err.println("producer terminates early: " + ex);
        }
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
