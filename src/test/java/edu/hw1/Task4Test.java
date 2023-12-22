package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    Task4 task4 = new Task4();
    @Test
    @DisplayName("Пустая строка")
    void fixEmpty(){
        String broken = "";
        String result = task4.fixString(broken);
        assertThat(result).isEqualTo(broken);
    }

    @Test
    @DisplayName("Единичная строка")
    void fixLengthOne(){
        String broken = "y";
        String result = task4.fixString(broken);
        assertThat(result).isEqualTo(broken);
    }

    @Test
    @DisplayName("Обычная строка")
    void fixLengthMore(){
        String broken = "укук  вава";
        String answer = "куку  авав";
        String result = task4.fixString(broken);
        assertThat(result).isEqualTo(answer);
    }
}
