package edu.hw_9.task1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class Aggregator implements AutoCloseable {
    final BlockingQueue<PTask> queue = new ArrayBlockingQueue<>(10);
    final Map<PTask, Answer> stats = new ConcurrentHashMap<>();
    final PTask[] producerTasks;
    final CTask[] consumerTasks;
    final List<Thread> consumerThreads;

    final List<Thread> producerThreads;

    public Aggregator(PTask[] producerTasks, CTask[] consumerTasks) {
        this.producerTasks = producerTasks;
        this.consumerTasks = consumerTasks;
        consumerThreads = new ArrayList<>(consumerTasks.length);
        producerThreads = new ArrayList<>(producerTasks.length);
    }

    public Map<PTask, Answer> getStats() {
        return this.stats;
    }

    public void makeStats() {
        for (PTask pTask : producerTasks) {
            producerThreads.add(new Thread(new Producer(this.queue, pTask)));
            var thread = producerThreads.getLast();
            thread.start();
        }
        for (CTask cTask : consumerTasks) {
            consumerThreads.add(new Thread(new Consumer(this.stats, this.queue, cTask)));
            var thread = consumerThreads.getLast();
            thread.start();
        }
    }

    @Override
    public void close() throws Exception {
        for (Thread t : consumerThreads) {
            t.interrupt();
        }
        for (Thread t : producerThreads) {
            t.interrupt();
        }
    }
}
