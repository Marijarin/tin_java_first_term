package edu.hw_9;

import edu.hw_9.task3.Cell;
import edu.hw_9.task3.DFSParallel;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void findsSolutionDFS() {
        int[][] maze = {
            {1, 0},
            {1, 1}
        };

        DFSParallel dfs = new DFSParallel(maze, new Cell(0, 0), new boolean[maze.length][maze[0].length]);
        List<Cell> path;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            path = forkJoinPool.invoke(dfs);
        }

        assertThat(path).isEqualTo(List.of(new Cell(0, 0), new Cell(1, 0), new Cell(1, 1)));
    }

    @Test
    void findsNoSolutionDFS() {
        int[][] maze = {
            {1, 0},
            {0, 1}
        };

        DFSParallel dfs = new DFSParallel(maze, new Cell(0, 0), new boolean[maze.length][maze[0].length]);
        List<Cell> path;
        try (ForkJoinPool forkJoinPool = ForkJoinPool.commonPool()) {
            path = forkJoinPool.invoke(dfs);
        }

        assertThat(path).isEqualTo(null);
    }
}

