package edu.hw_8;

import edu.hw_8.Task2.FibonacciCounter;
import edu.hw_8.Task2.another_one.CustomExecutorService;
import edu.hw_8.Task2.another_one.CustomExecutors;
import edu.hw_8.Task2.another_one.FibTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) throws Exception {
        long start1 = System.nanoTime() / 1_000_000;
        FibonacciCounter fibonacciCounter1 = new FibonacciCounter(5);
        LOGGER.info(fibonacciCounter1.startCount(6));
        long end1 = System.nanoTime() / 1_000_000;
        long start2 = System.nanoTime() / 1_000_000;
        FibonacciCounter fibonacciCounter2 = new FibonacciCounter(1);

        LOGGER.info(fibonacciCounter2.startCount(6));
        long end2 = System.nanoTime() / 1_000_000;
        LOGGER.info("5 threads: {}", end1 - start1);
        LOGGER.info("1 thread: {}", end2 - start2);
        long start3 = System.nanoTime() / 1_000_000;
        try (CustomExecutorService service = CustomExecutors.FixedThreadPool(5)) {
            for (int i = 0; i <= 6; i++) {
                service.execute(new FibTask(i));
            }
            Thread.sleep(1);
            long end3 = System.nanoTime() / 1_000_000;
            LOGGER.info("new thread pool 5 : {}", end3 - start3);
        }
    }
}
