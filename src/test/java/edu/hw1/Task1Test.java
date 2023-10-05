package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Секунд слишком много")
    void tooManySec() {
        String vLength = "13:60";
        int answer = -1;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Некорректный ввод")
    void wrongInput() {
        String vLength = "a13:11";
        int answer = -1;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Некорректный ввод в конце")
    void wrongInputEnd() {
        String vLength = "13:11 min";
        int answer = -1;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Некорректный ввод отрицательный")
    void wrongInputMinus() {
        String vLength = "-13:11";
        int answer = -1;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Корректный ввод")
    void correctInput() {
        String vLength = "13:50";
        int answer = 13 * 60 + 50;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("С пробелами")
    void spacedInput() {
        String vLength = " 13 : 50 ";
        int answer = 13 * 60 + 50;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }
    @Test
    @DisplayName("Нулевая длина")
    void zeroInput() {
        String vLength = " 00 : 00 ";
        int answer = 0;
        int result = Task1.minutesToSeconds(vLength);
        assertThat(result).isEqualTo(answer);
    }

}
