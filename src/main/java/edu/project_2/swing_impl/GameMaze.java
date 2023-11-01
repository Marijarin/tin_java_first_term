package edu.project_2.swing_impl;


    import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

    public class GameMaze {
        public static void main(String[] args) {
            JFrame frame = new JFrame("Лабиринт");
            JLabel label = new JLabel("Найдите выход, он зеленый");
            Maze maze = new Maze();
            frame.add(label, BorderLayout.NORTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.add(maze);
            frame.setResizable(false);
            frame.pack();
            frame.setVisible(true);
            label.setFont(new Font("Arial", Font.BOLD, 13));
            label.setHorizontalAlignment(JLabel.CENTER);
        }
    }

    class Maze extends JPanel implements ActionListener {

        MazeGenerator mg = new MazeGenerator(15, 15, new int[]{0, 0}, new int[]{14, 14});
        final private int[][] maze = mg.maze;
        private final int blockSize = 40;
        private final int numRows = maze.length;
        private final int numCols = maze[0].length;
        private Timer timer;
        private Player player;

        public Maze() {
            setPreferredSize(new Dimension(numCols * blockSize, numRows * blockSize));
            setFocusable(true);
            addKeyListener(new TAdapter());
            setBackground(Color.CYAN);

            player = new Player();
            timer = new Timer(25, this);
            timer.start();
        }



        private class Player {
            int x, y;

            Player() {
                x = 0;
                y = 0;
            }
            public void won() {
                JOptionPane.showMessageDialog(null, "You Won!", "Congratulation", JOptionPane.INFORMATION_MESSAGE);
            }

            public void move(int dx, int dy) {
                int nextX = x + dx;
                int nextY = y + dy;

                int row = nextY / blockSize;
                int col = nextX / blockSize;
                if (!mg.isAvailable(row, col)) return;

                if (maze[row][col] == 0) {
                    x = nextX;
                    y = nextY;
                }
                if(maze[row][col] == 5){
                    won();
                }
            }

        }
        private class TAdapter extends KeyAdapter{
            public void keyPressed (KeyEvent e){
                int key = e.getKeyCode();

                if(key == KeyEvent.VK_LEFT){
                    player.move(-blockSize, 0);
                }
                else if(key == KeyEvent.VK_RIGHT){
                    player.move(blockSize, 0);
                }
                else if(key == KeyEvent.VK_UP){
                    player.move(0,-blockSize);
                }
                else if(key == KeyEvent.VK_DOWN){
                    player.move(0, blockSize);
                }
                repaint();
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            for (int row = 0; row < maze.length; row++){
                for (int col = 0; col < maze[0].length; col++) {
                    if(maze[row][col] == 1){
                        g.setColor(Color.BLACK);
                        g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                    }
                    if(maze[row][col] == 5){
                        g.setColor(Color.BLACK);
                        g.fillRect(col * blockSize, row * blockSize, blockSize, blockSize);
                    }
                }

            }
            g.setColor(Color.ORANGE);
            g.fillOval(player.x, player.y, blockSize, blockSize);
            g.setColor(Color.GREEN);
            g.fillRect((numCols-1)*blockSize, (numRows-1)*blockSize, blockSize, blockSize);
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }
    }

