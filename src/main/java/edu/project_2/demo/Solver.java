package edu.project_2.demo;

import edu.project_2.demo.Coordinate;
import edu.project_2.demo.Maze;
import java.util.List;

public interface Solver {
    List<Coordinate> solve(Maze maze, Coordinate start, Coordinate end);
}
