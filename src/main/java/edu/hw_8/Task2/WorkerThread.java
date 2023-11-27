package edu.hw_8.Task2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.concurrent.LinkedBlockingQueue;

public class WorkerThread extends Thread {
    private final LinkedBlockingQueue<Runnable> queue;
    private final Logger LOGGER = LogManager.getLogger();

    boolean isThreadRunning = false;

    public WorkerThread(LinkedBlockingQueue<Runnable> queue) {
        this.queue = queue;
    }

    public void run() {
        Runnable task;
        while (isThreadRunning) {
                while (queue.isEmpty()) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        LOGGER.info("An error occurred while queue is waiting: {}", e.getMessage());
                    }
                }
                task = queue.poll();
            try {
                task.run();
            } catch (RuntimeException e) {
                LOGGER.info("Thread pool is interrupted : {}", e.getMessage());
            }
        }
    }
}

