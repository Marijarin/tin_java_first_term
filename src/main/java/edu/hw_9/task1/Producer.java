package edu.hw_9.task1;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable{
    final BlockingQueue<Integer> queue;
    final PTask pTask;

    public Producer(BlockingQueue<Integer> queue, PTask pTask) {
        this.queue = queue;
        this.pTask = pTask;
    }


    @Override
    public void run() {

    }
}
