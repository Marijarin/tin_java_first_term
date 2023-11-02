package edu.project_2.dfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Random;

class MazeGenerator {

    private final Deque<Cell> stack = new ArrayDeque<>();
    private final Random rand = new Random();
    int[][] maze;
    private final int dimension;

    MazeGenerator(int dim) {
        maze = new int[dim][dim];
        dimension = dim;
        generateMaze();
    }

    private void generateMaze() {
        stack.push(new Cell(0, 0));
        while (!stack.isEmpty()) {
            Cell next = stack.pop();
            if (validNextCell(next)) {
                maze[next.y][next.x] = 1;
                ArrayList<Cell> neighbors = findNeighbors(next);
                randomlyAddCellsToStack(neighbors);
            }
        }
        maze[maze.length - 1][maze.length - 1] = 1;
    }

    private boolean validNextCell(Cell Cell) {
        int numNeighboringOnes = 0;
        for (int y = Cell.y - 1; y < Cell.y + 2; y++) {
            for (int x = Cell.x - 1; x < Cell.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCell(Cell, x, y) && maze[y][x] == 1) {
                    numNeighboringOnes++;
                }
            }
        }
        return (numNeighboringOnes < 3) && maze[Cell.y][Cell.x] != 1;
    }

    private void randomlyAddCellsToStack(ArrayList<Cell> Cells) {
        int targetIndex;
        while (!Cells.isEmpty()) {
            targetIndex = rand.nextInt(Cells.size());
            stack.push(Cells.remove(targetIndex));
        }
    }

    private ArrayList<Cell> findNeighbors(Cell Cell) {
        ArrayList<Cell> neighbors = new ArrayList<>();
        for (int y = Cell.y - 1; y < Cell.y + 2; y++) {
            for (int x = Cell.x - 1; x < Cell.x + 2; x++) {
                if (pointOnGrid(x, y) && pointNotCorner(Cell, x, y)
                    && pointNotCell(Cell, x, y)) {
                    neighbors.add(new Cell(x, y));
                }
            }
        }
        return neighbors;
    }

    private Boolean pointOnGrid(int x, int y) {
        return x >= 0 && y >= 0 && x < dimension && y < dimension;
    }

    private Boolean pointNotCorner(Cell Cell, int x, int y) {
        return (x == Cell.x || y == Cell.y);
    }

    private Boolean pointNotCell(Cell Cell, int x, int y) {
        return !(x == Cell.x && y == Cell.y);
    }
}
