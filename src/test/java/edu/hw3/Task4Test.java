package edu.hw3;

import edu.hw3.task4.Task4;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {
    @Test
    @DisplayName("3")
    void convertWithSeveralI() {
        int input = 3;
        Task4 t4 = new Task4();

        String result = t4.convertToRoman(input);
        String output = "III";

        assertThat(result).isEqualTo(output);

    }

    @Test
    @DisplayName("99")
    void convertWithSeveral() {
        int input = 99;
        Task4 t4 = new Task4();

        String result = t4.convertToRoman(input);
        String output = "XCIX";

        assertThat(result).isEqualTo(output);

    }

    @Test
    @DisplayName("5")
    void convertWithSingle() {
        int input = 5;
        Task4 t4 = new Task4();

        String result = t4.convertToRoman(input);
        String output = "V";

        assertThat(result).isEqualTo(output);

    }

    @Test
    @DisplayName("Not correct")
    void convertNeg() {
        int input = -5;
        Task4 t4 = new Task4();

        String result = t4.convertToRoman(input);
        String output = "Can not be converted!";

        assertThat(result).isEqualTo(output);

    }
}
