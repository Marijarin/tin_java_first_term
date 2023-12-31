package edu.project_2.solvers;

import edu.project_2.Cell;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Maze extends JPanel implements ActionListener {
    final int[][] maze;
    private final int blockSize = 20;
    private final Player player;

    @SuppressWarnings("MagicNumber")
    public Maze(MazeGenerator mg) {
        maze = mg.maze;
        setPreferredSize(new Dimension(maze[0].length * blockSize, maze.length * blockSize));
        setFocusable(true);
        addKeyListener(new TAdapter());
        setBackground(Color.CYAN);
        player = new Player();
        Timer timer = new Timer(25, this);
        timer.start();
    }

    @SuppressWarnings("MagicNumber")
    public Maze(int[][] maze) {
        this.maze = maze;
        setPreferredSize(new Dimension(maze[0].length * blockSize, maze.length * blockSize));
        setFocusable(true);
        addKeyListener(new TAdapter());
        setBackground(Color.CYAN);
        player = new Player();
        Timer timer = new Timer(25, this);
        timer.start();
    }

    public String getPrintableMaze() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if ((i == 0 && j == 0) || (i == maze.length - 1 && j == maze.length - 1)) {
                    sb.append("->");
                }
                sb.append(maze[i][j] == 1 ? "  " : "██");
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String showExitPath(List<Cell> backtrackPath) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze.length; j++) {
                if ((i == 0 && j == 0) || (i == maze.length - 1 && j == maze.length - 1)) {
                    sb.append("->");
                }
                if (backtrackPath.contains(new Cell(i, j))) {
                    sb.append(" ◌");
                } else {
                    sb.append(maze[i][j] == 1 ? "  " : "██");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public String printMaze() {
        StringBuilder sb = new StringBuilder();
        for (int[] ints : maze) {
            for (int j = 0; j < maze.length; j++) {
                sb.append(ints[j] == 1 ? "1" : "0");
                sb.append("  ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row = 0; row < maze.length; row++) {
            for (int col = 0; col < maze.length; col++) {
                if (maze[row][col] != 1) {
                    g.setColor(Color.BLACK);
                    g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                }
            }
        }
        g.setColor(Color.MAGENTA);
        g.fillOval(player.x, player.y, blockSize, blockSize);
        g.setColor(Color.GREEN);
        g.fillRect((maze[0].length - 1) * blockSize, (maze.length - 1) * blockSize, blockSize, blockSize);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    private class Player {
        int x;
        int y;

        Player() {
            x = 0;
            y = 0;
        }

        public void won() {
            JOptionPane.showMessageDialog(null, "Выиграл!", "Молодец", JOptionPane.INFORMATION_MESSAGE);
        }

        public void move(int dx, int dy) {
            int nextX = x + dx;
            int nextY = y + dy;
            int row = nextY / blockSize;
            int col = nextX / blockSize;
            if (!isAvailable(row, col)) {
                return;
            }
            if (maze[row][col] == 1) {
                x = nextX;
                y = nextY;
            }
            if (row == maze.length - 1 && col == maze[0].length - 1) {
                won();
            }
        }

        public boolean isAvailable(int x, int y) {
            return x >= 0 && x < maze.length && y >= 0 && y < maze[0].length;
        }
    }

    private class TAdapter extends KeyAdapter {
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();

            if (key == KeyEvent.VK_LEFT) {
                player.move(-blockSize, 0);
            } else if (key == KeyEvent.VK_RIGHT) {
                player.move(blockSize, 0);
            } else if (key == KeyEvent.VK_UP) {
                player.move(0, -blockSize);
            } else if (key == KeyEvent.VK_DOWN) {
                player.move(0, blockSize);
            }
            repaint();
        }
    }
}
