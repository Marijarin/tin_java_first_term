package edu.hw_9.task1;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    final BlockingQueue<PTask> queue;
    final PTask pTask;

    public Producer(BlockingQueue<PTask> queue, PTask pTask) {
        this.queue = queue;
        this.pTask = pTask;
    }

    @Override
    public void run() {
        try {
            queue.put(this.pTask);
        } catch (InterruptedException ex) {
            System.err.println("producer terminates early: " + ex);
        }
    }
}
