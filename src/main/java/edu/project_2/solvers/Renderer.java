package edu.project_2.solvers;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Renderer {
    private Renderer() {
    }

    @SuppressWarnings({"UncommentedMain", "MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        JFrame frame = new JFrame("Лабиринт");
        JLabel label = new JLabel("Найдите выход, он зеленый");
        Maze maze = new Maze(new MazeGenerator(10));
        BFSSolver bfsSolver = new BFSSolver(maze.maze);
        DFSSolverRec dfsSolverRec = new DFSSolverRec(maze.maze);
        AStarSolver aStarSolver = new AStarSolver(maze.maze);
        frame.add(label, BorderLayout.NORTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(maze);
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
        label.setFont(new Font("Arial", Font.BOLD, 14));
        label.setHorizontalAlignment(JLabel.CENTER);
        System.out.println(maze.getPrintableMaze());
        System.out.println(maze.printMaze());
        System.out.println("BFS: \n" + maze.showExitPath(bfsSolver.solve()));
        System.out.println("DFS: \n" + maze.showExitPath(dfsSolverRec.solve()));
        System.out.println("AStar: \n" + maze.showExitPath(aStarSolver.solve()));
    }
}

