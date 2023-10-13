package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    Task2 task2 = new Task2();

    @Test
    @DisplayName("Положительное число")
    void positive(){
        long num1 = 123456789;
        int answer = 9;
        int result = task2.countDigits(num1);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Отрицательное число")
    void negative(){
        long num1 = -123456789;
        int answer = 9;
        int result = task2.countDigits(num1);
        assertThat(result).isEqualTo(answer);
    }
}
