package edu.hw_8.Task2.another_one;

public interface CustomExecutorService {
    void submit(Runnable runnable) throws InterruptedException;
}

