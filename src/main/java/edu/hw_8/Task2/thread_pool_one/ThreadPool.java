package edu.hw_8.Task2.thread_pool_one;

public interface ThreadPool extends AutoCloseable {
    void start();

    void execute(Runnable runnable) throws InterruptedException;
}
