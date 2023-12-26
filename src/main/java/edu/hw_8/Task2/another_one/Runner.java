package edu.hw_8.Task2.another_one;

class Runner implements Runnable {
    void executeMethod() {
        if (CustomThreadPool.currentCapacity < CustomThreadPool.capacity) {
            CustomThreadPool.currentCapacity++;
            Thread t = new Thread(new Runner());
            t.start();
        }
    }

    @Override
    public void run() {
        while (!CustomThreadPool.linkedBlockingQueue.isEmpty()) {
            CustomThreadPool.linkedBlockingQueue.poll().run();
        }
    }

    public void stop() {
        Thread.currentThread().interrupt();
    }
}
