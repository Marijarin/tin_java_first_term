package edu.project_2;

import edu.project_2.solvers.AStarSolver;
import edu.project_2.solvers.BFSSolver;
import edu.project_2.solvers.DFSSolverRec;
import edu.project_2.solvers.Maze;
import edu.project_2.solvers.MazeGenerator;
import java.util.List;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ProjectTest {
    @Test
    void drawCorrectLabyrinth() {
        int[][] m = {
            {1, 0},
            {0, 1}
        };
        Maze maze = new Maze(m);
        String mToPrint = maze.getPrintableMaze();

        assertThat(mToPrint).isEqualTo("->   ██ \n██ ->   \n");
    }

    @Test
    void badDimension() {
        AssertionsForClassTypes.assertThatThrownBy(() -> new MazeGenerator(1)).isInstanceOf(RuntimeException.class);
    }

    @Test
    void findsSolutionBFS() {
        int[][] m = {
            {1, 0},
            {1, 1}
        };
        BFSSolver bfs = new BFSSolver(m);

        var result = bfs.solve();

        assertThat(result).isEqualTo(List.of(new Cell(0, 0), new Cell(1, 0), new Cell(1, 1)));
    }

    @Test
    void findsNoSolutionBFS() {
        int[][] m = {
            {1, 0},
            {0, 1}
        };
        BFSSolver bfs = new BFSSolver(m);

        var result = bfs.solve();

        assertThat(result).isEqualTo(List.of());
    }

    @Test
    void findsSolutionDFS() {
        int[][] m = {
            {1, 0},
            {1, 1}
        };
        DFSSolverRec dfs = new DFSSolverRec(m);

        var result = dfs.solve();

        assertThat(result).isEqualTo(List.of(new Cell(0, 0), new Cell(1, 0), new Cell(1, 1)));
    }

    @Test
    void findsNoSolutionDFS() {
        int[][] m = {
            {1, 0},
            {0, 1}
        };
        DFSSolverRec bfs = new DFSSolverRec(m);

        var result = bfs.solve();

        assertThat(result).isEqualTo(List.of());
    }

    @Test
    void findsSolutionAStar() {
        int[][] m = {
            {1, 0},
            {1, 1}
        };
        var aStar = new AStarSolver(m);

        var result = aStar.solve();

        assertThat(result).isEqualTo(List.of(new Cell(0, 0), new Cell(1, 0), new Cell(1, 1)));
    }

    @Test
    void findsNoSolutionAStar() {
        int[][] m = {
            {1, 0},
            {0, 1}
        };
        var aStar = new AStarSolver(m);

        var result = aStar.solve();

        assertThat(result).isEqualTo(List.of());
    }
}
