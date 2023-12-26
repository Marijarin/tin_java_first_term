package edu.hw_8.Task2.thread_pool_one;

import java.util.concurrent.LinkedBlockingQueue;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WorkerThread extends Thread {
    private final LinkedBlockingQueue<Runnable> queue;
    private final Logger logger = LogManager.getLogger();

    boolean isThreadRunning = false;

    public WorkerThread(LinkedBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        Runnable task;
        while (isThreadRunning) {
            task = queue.poll();
            try {
                if (task != null) {
                    task.run();
                }
            } catch (RuntimeException e) {
                logger.info("Thread pool is interrupted : {}", e.getMessage());
            }
        }
    }
}

