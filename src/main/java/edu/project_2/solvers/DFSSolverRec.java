package edu.project_2.solvers;

import edu.project_2.Cell;
import edu.project_2.Solver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DFSSolverRec implements Solver {
    private final int[][] maze;
    private final boolean[][] visited;

    public DFSSolverRec(int[][] maze) {
        this.maze = maze;
        visited = new boolean[maze.length][maze.length];
    }

    @Override public List<Cell> solve() {
        List<Cell> path = new ArrayList<>();
        if (explore(getEntry().x(), getEntry().y(), path)) {
            return path;
        }
        return Collections.emptyList();
    }

    private boolean explore(int x, int y, List<Cell> path) {
        if (!isValidLocation(x, y) || isWall(x, y) || visited[x][y]) {
            return false;
        }
        path.add(new Cell(x, y));
        setVisited(x, y);
        if (new Cell(x, y).equals(getExit())) {
            return true;
        }
        for (int[] direction : DIRECTIONS) {
            Cell cell = getNextCell(x, y, direction[0], direction[1]);
            if (explore(cell.x(), cell.y(), path)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    private Cell getNextCell(int x, int y, int i, int j) {
        return new Cell(x + i, y + j);
    }

    private Cell getEntry() {
        return new Cell(0, 0);
    }

    private Cell getExit() {
        return new Cell(maze.length - 1, maze.length - 1);
    }

    private boolean isWall(int x, int y) {
        return maze[x][y] == 0;
    }

    private void setVisited(int x, int y) {
        visited[x][y] = true;
    }

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x <= maze.length - 1 && y >= 0 && y <= maze.length - 1;
    }
}


