package edu.hw_8.Task2.thread_pool_one;

import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final WorkerThread[] threads;
    private final LinkedBlockingQueue<Runnable> tasksQueue;

    public FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        tasksQueue = new LinkedBlockingQueue<>();
        threads = new WorkerThread[threadsNumber];

    }

    @Override public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new WorkerThread(tasksQueue);
            threads[i].isThreadRunning = true;
            threads[i].start();
        }
    }

    @Override
    public void execute(Runnable runnable) throws InterruptedException {
        tasksQueue.offer(runnable);
        this.start();
    }

    @Override
    public void close() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i].isThreadRunning = false;
            threads[i].interrupt();
        }
    }
}
