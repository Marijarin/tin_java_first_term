package edu.project_2.dfs;

import edu.project_2.Cell;
import edu.project_2.Solver;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class BFSSolver implements Solver {
    private final int[][] maze;
    private final Cell[][] backtrackPath;

    public BFSSolver(int[][] maze) {
        this.maze = maze;
        backtrackPath = new Cell[maze.length][maze.length];
    }

    @Override
    public List<Cell> solve() {
        Deque<Cell> nextToVisit = new ArrayDeque<>();
        Cell start = getEntry();
        nextToVisit.addLast(start);
        backtrackPath[start.x()][start.y()] = start;
        while (!nextToVisit.isEmpty()) {
            Cell current = nextToVisit.pollFirst();
            goToDirections(nextToVisit, current);
        }
        return showPath();
    }

    private void goToDirections(Deque<Cell> nextToVisit, Cell current) {
        for (int[] direction : DIRECTIONS) {
            Cell next = new Cell(current.x() + direction[0], current.y() + direction[1]);
            if (!isValidLocation(next.x(), next.y()) || isWall(next.x(), next.y())
                || backtrackPath[next.x()][next.y()] != null) {
                continue;
            }
            nextToVisit.addLast(next);
            backtrackPath[next.x()][next.y()] = current;
        }
    }

    private List<Cell> showPath() {
        Deque<Cell> path = new ArrayDeque<>();
        Cell now = getExit();
        while (!(now == null || now.equals(getEntry()))) {
            path.addFirst(now);
            now = backtrackPath[now.x()][now.y()];
        }
        path.addFirst(getEntry());
        if (now == null) {
            return Collections.emptyList();
        }
        return new LinkedList<>(path);
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

    private boolean isValidLocation(int x, int y) {
        return x >= 0 && x <= maze.length - 1 && y >= 0 && y <= maze.length - 1;
    }

}
