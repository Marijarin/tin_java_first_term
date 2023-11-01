package edu.project_2.demo;

import edu.project_2.demo.Coordinate;
import edu.project_2.demo.Maze;
import java.util.List;

public interface Renderer {
    String render(Maze maze);
    String render(Maze maze, List<Coordinate> path);
}

