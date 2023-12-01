package edu.hw_8.Task2;

import java.util.concurrent.LinkedBlockingQueue;

public class FixedThreadPool implements ThreadPool {
    private final int threadsNumber;
    private final WorkerThread[] threads;
    private final LinkedBlockingQueue<Runnable> tasksQueue;

    public FixedThreadPool(int threadsNumber) {
        this.threadsNumber = threadsNumber;
        tasksQueue = new LinkedBlockingQueue<>();
        threads = new WorkerThread[threadsNumber];
        this.start();
    }

    @Override public void start() {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new WorkerThread(tasksQueue);
            threads[i].isThreadRunning = true;
            threads[i].start();
        }
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        tasksQueue.offer(runnable);
    }

    @Override
    public void close() throws InterruptedException {
        for (int i = 0; i < threadsNumber; i++) {
            threads[i] = new WorkerThread(tasksQueue);
            threads[i].join();
            threads[i].isThreadRunning = false;
        }
    }
}
