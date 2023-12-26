package edu.hw_8.Task2.another_one;

public interface CustomExecutorService extends AutoCloseable  {
    void execute(Runnable runnable) throws InterruptedException;
}

