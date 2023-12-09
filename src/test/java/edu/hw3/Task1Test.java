package edu.hw3;

import edu.hw3.task1.Task1;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {

    @Test
    @DisplayName("Hello world!")
    void inputToCipher() {
        String input = "Hello world!";
        String output = "Svool dliow!";

        Task1 t1 = new Task1();
        String result = t1.atbash(input);

        assertThat(result).isEqualTo(output);

    }

    @Test
    @DisplayName("Привет мир!")
    void inputNotToCipher() {
        String input = "Привет мир!";

        Task1 t1 = new Task1();
        String result = t1.atbash(input);

        assertThat(result).isEqualTo(input);
    }

    @Test
    @DisplayName("Привет world!")
    void inputSomeNotToCipher() {
        String input = "Привет world!";
        String output = "Привет dliow!";
        Task1 t1 = new Task1();
        String result = t1.atbash(input);

        assertThat(result).isEqualTo(output);
    }

    @Test
    @DisplayName("Smart words")
    void inputComplexToCipher() {
        String input =
            "Any fool can write code that y computer can understand. Good programmers write code that humans can understand. ― Martin Fowler";
        String output =
            "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw. Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi";
        Task1 t1 = new Task1();
        String result = t1.atbash(input);

        assertThat(result).isEqualTo(output);
    }
}
