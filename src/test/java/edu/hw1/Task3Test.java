package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    Task3 task3 = new Task3();

    @Test
    @DisplayName("Пустой внутри непустого")
    void emptyNestable() {
        int[] into = {};
        int[] outer = {0, 1};
        boolean result = task3.isNestable(into, outer);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Непустой внутри пустого")
    void inEmptyNestable() {
        int[] into = {0, 1};
        int[] outer = {};
        boolean result = task3.isNestable(into, outer);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Влезает")
    void isNestableTrue() {
        int[] into = {0, 1};
        int[] outer = {-1, 2};
        boolean result = task3.isNestable(into, outer);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Не влезает")
    void isNestableFalse() {
        int[] into = {-1, 2};
        int[] outer = {0, 1};
        boolean result = task3.isNestable(into, outer);
        assertThat(result).isFalse();
    }

}
