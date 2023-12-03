package edu.hw_8.Task2.another_one;

import java.util.concurrent.LinkedBlockingQueue;

class CustomThreadPool implements CustomExecutorService {
    static int capacity;
    static int currentCapacity;
    static LinkedBlockingQueue<Runnable> linkedBlockingQueue;
    Runner runner;

    CustomThreadPool(int capacity) {
        CustomThreadPool.capacity = capacity;
        currentCapacity = 0;
        linkedBlockingQueue = new LinkedBlockingQueue<>();
        runner = new Runner();
    }

    @Override
    public void execute(Runnable r) {
        linkedBlockingQueue.add(r);
        runner.executeMethod();
    }

    @Override
    public void close() {
        runner.stop();
    }
}




