package edu.hw_9;

import java.util.concurrent.ForkJoinPool;
import edu.hw_9.task3.Cell;
import edu.hw_9.task3.DFSParallel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
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
        var visited = new boolean[maze.length][maze[0].length];
        try (ForkJoinPool forkJoinPool = new ForkJoinPool(8)) {
            var path = forkJoinPool.invoke(new DFSParallel(maze, new Cell(0, 0), visited));
            for (Cell step : path) {
                System.out.print(step + " ");
            }
        }
    }
}
