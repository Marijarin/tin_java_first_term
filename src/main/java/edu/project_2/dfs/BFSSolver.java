package edu.project_2.dfs;

import edu.project_2.Cell;
import edu.project_2.Solver;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

public class BFSSolver implements Solver {
    private final int[][] maze;
    private final boolean[][] visited;
    private final Cell[][] backtrackPath;

    public BFSSolver(int[][] maze) {
        this.maze = maze;
        visited = new boolean[maze.length][maze.length];
        backtrackPath = new Cell[maze.length][maze.length];
    }

    @Override
    public List<Cell> solve() {
        Deque<Cell> nextToVisit = new ArrayDeque<>();
        Cell start = getEntry();
        nextToVisit.add(start);
        setVisited(start.x(), start.y());
        backtrackPath[start.x()][start.y()] = start;
        while (!nextToVisit.isEmpty()) {
            Cell current = nextToVisit.pollFirst();

            for (int[] direction : DIRECTIONS) {
                Cell next = new Cell(current.x() + direction[0], current.y() + direction[1]);
                if (!isValidLocation(next.x(), next.y()) || isWall(next.x(), next.y()) || visited[next.x()][next.y()]) {
                    continue;
                }
                nextToVisit.addLast(next);
                setVisited(next.x(), next.y());
                backtrackPath[next.x()][next.y()] = current;
            }
        }
        Deque<Cell> path = new ArrayDeque<>();
        Cell now = getExit();
        while (!(now == null || now.equals(getEntry()))) {
            path.addFirst(now);
            now = backtrackPath[now.x()][now.y()];
        }
        if (now == null) {
            return Collections.emptyList();
        }
        List<Cell> plist = new ArrayList<>(path);
        plist.addAll(path);
        return plist;
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
