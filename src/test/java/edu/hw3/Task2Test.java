package edu.hw3;

import edu.hw3.task2.Task2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Balanced cluster")
    void balancedInput(){
        Task2 t2 = new Task2();

        var result = t2.clusterize("((()))(())()()(()())");
        var clusters = List.of("((()))", "(())", "()", "()", "(()())");

        assertThat(result).isEqualTo(clusters);
    }

    @Test
    @DisplayName("Not balanced cluster")
    void notBalancedInput(){
        Task2 t2 = new Task2();

        var result = t2.clusterize("((()))(())()()(()()");
        var clusters = List.of("((()))", "(())", "()", "()", "Current cluster is not balanced!");

        assertThat(result).isEqualTo(clusters);
    }
}
