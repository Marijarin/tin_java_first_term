package edu.hw_8.Task2.another_one;

@SuppressWarnings("MethodName")
public class CustomExecutors {
    private CustomExecutors() {
    }

    public static CustomExecutorService FixedThreadPool(int capacity) {
        return new CustomThreadPool(capacity);
    }
}
