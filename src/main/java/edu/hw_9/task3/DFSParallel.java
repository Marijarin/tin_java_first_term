package edu.hw_9.task3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;
import static java.util.Arrays.asList;

/**
 * It works for binary mazes.
 * Init in ForkJoinPool.
 **/
@SuppressWarnings("MagicNumber")
public class DFSParallel extends RecursiveTask<List<Cell>> {
    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public Cell start;
    private final int[][] maze;
    private final boolean[][] visited;

    public DFSParallel(int[][] maze, Cell start, boolean[][] visited) {
        this.maze = maze;
        this.visited = visited;
        this.start = start;

    }

    @Override
    protected List<Cell> compute() {
        if (!isValidLocation(start) || isWall(start) || visited[start.y()][start.x()]) {
            return null;
        }
        setVisited(start);
        if (start.equals(getExit())) {
            return new ArrayList<>(List.of(start));
        }
        DFSParallel[] tasksForCell = new DFSParallel[directions.length];
        List<List<Cell>> results = asList(null, null, null, null);
        for (int i = 0; i < 4; i++) {
            Cell cell = getNextCell(start.y(), start.x(), directions[i][0], directions[i][1]);
            tasksForCell[i] = new DFSParallel(maze, cell, visited);
            if (i < 3) {
                tasksForCell[i].fork();
            } else {
                results.set(3, tasksForCell[i].compute());
            }
        }
        for (int i = 0; i < 4; i++) {
            if (i < 3) {
                results.set(i, tasksForCell[i].join());
            }
            if (results.get(i) != null) {
                synchronized (this) {
                    List<Cell> result = new LinkedList<>();
                    result.add(this.start);
                    result.addAll(results.get(i));
                    return result;
                }
            }
        }
        return null;
    }

    private Cell getExit() {
        return new Cell(maze.length - 1, maze[0].length - 1);
    }

    private Cell getNextCell(int y, int x, int i, int j) {
        return new Cell(y + i, x + j);
    }

    private boolean isWall(Cell cell) {
        return maze[cell.y()][cell.x()] == 0;
    }

    private synchronized void setVisited(Cell cell) {
        visited[cell.y()][cell.x()] = true;
    }

    private boolean isValidLocation(Cell cell) {
        return cell.y() >= 0 && cell.y() <= maze.length - 1 && cell.x() >= 0 && cell.x() <= maze[0].length - 1;
    }
}
