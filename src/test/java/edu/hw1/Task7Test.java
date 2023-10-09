package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    Task7 task7 = new Task7();

    @Test @DisplayName("Нулевой сдвиг вправо") void emptyShiftRight() {
        int shift = 0;
        int n = 8;
        int answer = 8;
        int result = task7.rotateRight(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Нулевой сдвиг влево") void emptyShiftLeft() {
        int shift = 0;
        int n = 8;
        int answer = 8;
        int result = task7.rotateLeft(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Положительный сдвиг вправо") void posShiftRight() {
        int shift = 1;
        int n = 8;
        int answer = 4;
        int result = task7.rotateRight(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Положительный сдвиг влево") void posShiftLeft() {
        int shift = 2;
        int n = 17;
        int answer = 6;
        int result = task7.rotateLeft(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Отрицательный сдвиг вправо") void negShiftRight() {
        int shift = -2;
        int n = 17;
        int answer = 6;
        int result = task7.rotateRight(n, shift);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Отрицательный сдвиг влево") void negShiftLeft() {
        int shift = -1;
        int n = 8;
        int answer = 4;
        int result = task7.rotateLeft(n, shift);
        assertThat(result).isEqualTo(answer);
    }
}
