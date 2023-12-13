package edu.project_2;

import java.util.List;

public interface Solver {
    int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    List<Cell> solve();
}
