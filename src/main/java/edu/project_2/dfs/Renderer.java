package edu.project_2.dfs;

import java.awt.BorderLayout;
import java.awt.Font;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Renderer {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Лабиринт");
        JLabel label = new JLabel("Найдите выход, он зеленый");
        Maze maze = new Maze(new MazeGenerator(50));
        BFSSolver bfsSolver = new BFSSolver(maze.maze);
        DFSSolver dfsSolver = new DFSSolver(maze.maze);
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
        System.out.println(maze.showExitPath(bfsSolver.solve()));
        System.out.println(maze.showExitPath(dfsSolver.solve()));
    }
}

