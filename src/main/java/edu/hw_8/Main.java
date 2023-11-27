package edu.hw_8;

import edu.hw_8.Task2.FibonacciCounter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) throws InterruptedException {
       /* MD5Decoder md5Decoder = new MD5Decoder();
        md5Decoder.nextPassword();
        LOGGER.info(md5Decoder.deciphered);
        DecoderThreadsStats decoderThreadsStats = new DecoderThreadsStats();
        System.out.println(decoderThreadsStats.showThreadsStats());*/
        FibonacciCounter fibonacciCounter1 = new FibonacciCounter(10);
        FibonacciCounter fibonacciCounter2 = new FibonacciCounter(1);
        long start1 = System.nanoTime() / 1_000_000;
        LOGGER.info(fibonacciCounter1.startCount(5));
        long end1 = System.nanoTime() / 1_000_000;
        long start2 = System.nanoTime() / 1_000_000;
        LOGGER.info(fibonacciCounter2.startCount(5));
        long end2 = System.nanoTime() / 1_000_000;
        LOGGER.info("10 threads: {}", end1 - start1);
        LOGGER.info("1 thread: {}", end2 - start2);
    }
}
