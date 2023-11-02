package edu.project_2.dfs;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MazeGenerator1 {
    private static final int WALL = 1;
    private static final int EMPTY = 0;
    private static final int START = 0;
    private static final int END = 5;

    private static final int[][] DIR = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
    private final int n, m;
    public final int[][] maze;

    private final Random random;

    public MazeGenerator1(int n, int m, int[] start, int[] end) {
        this.n = n;
        this.m = m;
        this.random = new Random();
        this.maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(maze[i], WALL);
        }

        maze[start[0]][start[1]] = START;
        maze[end[0]][end[1]] = END;

        dfs(start[0], start[1]);
    }

    public boolean isAvailable(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private void dfs(int x, int y) {
        List<Integer> directions = Arrays.asList(0, 1, 2, 3);
        Collections.shuffle(directions, random);
        int iterated = 0;
        for (int d : directions) {
            int nx = x + DIR[d][0];
            int ny = y + DIR[d][1];
            if (!isAvailable(nx, ny) || maze[nx][ny] != WALL) {
                continue;
            }
            maze[x][y] = EMPTY;
            dfs(nx, ny);
            if (iterated >= 1) break;
            iterated++;
        }

    }
}




