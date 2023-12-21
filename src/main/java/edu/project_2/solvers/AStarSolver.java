package edu.project_2.solvers;

import edu.project_2.Cell;
import edu.project_2.Solver;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.function.Predicate;

public class AStarSolver implements Solver {
    private final int[][] maze;

    public AStarSolver(int[][] maze) {
        this.maze = maze;
    }

    public List<Cell> solve() {
        List<Cell> path = new ArrayList<>();
        var node = aStar(this::isExit, this::successors);
        while (node != null) {
            path.add(node.state);
            node = node.parent;
        }
        return path.reversed();
    }

    public Node<Cell> aStar(
        Predicate<Cell> goalTest,
        Function<Cell, List<Cell>> successors
    ) {
        PriorityQueue<Node<Cell>> frontier = new PriorityQueue<>();
        frontier.offer(new Node<>(getEntry(), null, 0,
            manhattanDistance(getEntry())
        ));
        Map<Cell, Integer> explored = new HashMap<>();
        explored.put(getEntry(), 0);
        while (!frontier.isEmpty()) {
            Node<Cell> currentNode = frontier.poll();
            Cell currentState = currentNode.state;
            if (goalTest.test(currentState)) {
                return currentNode;
            }
            for (Cell child : successors.apply(currentState)) {
                int newCost = currentNode.cost + 1;
                if (!explored.containsKey(child) || explored.get(child) > newCost) {
                    explored.put(child, newCost);
                    frontier.offer(new Node<>(child, currentNode, newCost,
                        manhattanDistance(getEntry())
                    ));
                }
            }
        }
        return null;
    }

    public List<Cell> successors(Cell cell) {
        List<Cell> locations = new ArrayList<>();
        for (int[] direction : DIRECTIONS) {
            Cell child = getNextCell(cell.x(), cell.y(), direction[0], direction[1]);
            if (isValidLocation(child) && isNotWall(child)) {
                locations.add(child);
            }
        }
        return locations;
    }

    public int manhattanDistance(Cell cell) {
        int xDistance = Math.abs(cell.x() - getExit().x());
        int yDistance = Math.abs(cell.y() - getExit().y());
        return (xDistance + yDistance);
    }

    private Cell getExit() {
        return new Cell(maze.length - 1, maze.length - 1);
    }

    private Cell getEntry() {
        return new Cell(0, 0);
    }

    public boolean isExit(Cell cell) {
        return cell.x() == maze.length - 1 && cell.y() == maze[0].length - 1;
    }

    private boolean isNotWall(Cell cell) {
        return maze[cell.x()][cell.y()] != 0;
    }

    private Cell getNextCell(int x, int y, int i, int j) {
        return new Cell(x + i, y + j);
    }

    private boolean isValidLocation(Cell cell) {
        return cell.x() >= 0 && cell.x() <= maze.length - 1 && cell.y() >= 0 && cell.y() <= maze.length - 1;
    }
}
