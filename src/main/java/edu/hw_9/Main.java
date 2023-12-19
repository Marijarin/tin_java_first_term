package edu.hw_9;

import edu.hw_9.task1.Aggregator;
import edu.hw_9.task1.CTask;
import edu.hw_9.task1.PTask;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw_9.task1.CTask.AVERAGE;
import static edu.hw_9.task1.CTask.MAX;
import static edu.hw_9.task1.CTask.MIN;
import static edu.hw_9.task1.CTask.SUM;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws Exception {
        var maze = new int[][] {
//            {1, 0, 0},
//            {1, 1, 1},
//            {0, 0, 1}
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 0, 1, 1, 0, 1, 0, 1},
            {0, 0, 0, 0, 1, 0, 0, 0, 0, 1},
            {1, 1, 1, 0, 1, 1, 1, 0, 1, 0},
            {1, 0, 1, 1, 1, 1, 1, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 1, 0, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 1, 0, 0, 1}
        };
        var pTasks = new PTask[] {
            new PTask(new double[] {1.0, 2.0, 1.1, 2.2}),
            new PTask(new double[] {1.0, 3.0, 3.0, 1.0}),
            new PTask(new double[] {1.0, 3.1, 3.0, 1.0}),
            new PTask(new double[] {0.9, 3.0, 3.0, 0.8})
        };
        var cTasks = new CTask[] {
            SUM,
            AVERAGE,
            MAX,
            MIN
        };
        try (var aggregator = new Aggregator(pTasks, cTasks)) {
            aggregator.makeStats();
            Thread.sleep(10);
            LOGGER.info(aggregator.getStats());
        }
    }
}
