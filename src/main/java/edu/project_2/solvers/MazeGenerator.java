package edu.project_2.solvers;

import edu.project_2.Cell;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

public class MazeGenerator {
    private final Deque<Cell> q = new ArrayDeque<>();
    private final Random random = new Random();
    int[][] maze;
    private final int dimension;

    public MazeGenerator(int dimension) {
        if (dimension > 1) {
            maze = new int[dimension][dimension];
            this.dimension = dimension;
            generateMaze();
        } else {
            throw new RuntimeException("not valid dimension");
        }
    }

    private void generateMaze() {
        q.push(new Cell(0, 0));
        while (!q.isEmpty()) {
            Cell next = q.pop();
            if (validNextCell(next)) {
                maze[next.y()][next.x()] = 1;
                ArrayList<Cell> neighbors = findNeighbors(next);
                randomlyAddCellsToStack(neighbors);
            }
        }
        maze[dimension - 1][dimension - 1] = 1;
    }

    @SuppressWarnings("MagicNumber")
    private boolean validNextCell(Cell cell) {
        int numNeighboringOnes = 0;
        int neighborLimit = 3;
        for (int y = cell.y() - 1; y < cell.y() + 2; y++) {
            for (int x = cell.x() - 1; x < cell.x() + 2; x++) {
                if (isAvailable(x, y) && isNotVisited(cell, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < neighborLimit) && maze[cell.y()][cell.x()] != 1;
    }

    private void randomlyAddCellsToStack(ArrayList<Cell> cells) {
        int targetIndex;
        while (!cells.isEmpty()) {
            targetIndex = random.nextInt(cells.size());
            q.push(cells.remove(targetIndex));
        }
    }

    private ArrayList<Cell> findNeighbors(Cell cell) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        for (int y = cell.y() - 1; y < cell.y() + 2; y++) {
            for (int x = cell.x() - 1; x < cell.x() + 2; x++) {
                if (isAvailable(x, y) && isNotCorner(cell, x, y)
                    && isNotVisited(cell, x, y)) {
                    neighbors.add(new Cell(x, y));
                }
            }
        }
        return neighbors;
    }

    private Boolean isAvailable(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean isNotCorner(Cell cell, int x, int y) {
        return (x == cell.x() || y == cell.y());
    }

    private Boolean isNotVisited(Cell cell, int x, int y) {
        return !(x == cell.x() && y == cell.y());
    }
}
