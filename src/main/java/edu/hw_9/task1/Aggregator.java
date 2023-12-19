package edu.hw_9.task1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Aggregator {
    final BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(10);

    final PTask[] producerTasks;
    final CTask[] consumerTasks;

    public Aggregator(PTask[] producerTasks, CTask[] consumerTasks) {
        this.producerTasks = producerTasks;
        this.consumerTasks = consumerTasks;
    }

    public void makeStats() {
        for (PTask pTask : producerTasks) {
            new Thread(new Producer(this.queue, pTask)).start();
        }
        for (CTask cTask : consumerTasks) {
            new Thread(new Consumer(this.queue, cTask)).start();
        }
    }

}
