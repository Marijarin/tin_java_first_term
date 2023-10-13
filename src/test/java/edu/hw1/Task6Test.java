package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task6Test {
    Task6 task6 = new Task6();

    @Test
    @DisplayName("Слишком маленькое")
    void tooSmall() {
        int fD = 999;
        int answer = -1;
        int result = task6.countKSteps(fD);
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Слишком большое")
    void tooBig() {
        int fD = 99911;
        int answer = -1;
        int result = task6.countKSteps(fD);
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Число Капрекара")
    void zeroSteps() {
        int fD = 6174;
        int answer = 0;
        int result = task6.countKSteps(fD);
        assertThat(result).isEqualTo(answer);
    }

    @Test
    @DisplayName("Корректный ввод")
    void NotZeroSteps() {
        int fD = 6621;
        int answer = 5;
        int result = task6.countKSteps(fD);
        assertThat(result).isEqualTo(answer);
    }
}
