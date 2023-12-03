package edu.hw3;

import edu.hw3.task3.Task3;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    @DisplayName("Ints array")
    void intsFrequency() {
        Task3 t3 = new Task3();
        var result = t3.freqDict(new Integer[] {1, 1, 2, 2});

        var output = new HashMap<Integer, Integer>();
        output.put(1, 2);
        output.put(2, 2);

        assertThat(result).isEqualTo(output);
    }

    @Test
    @DisplayName("Strings array")
    void stringsFrequency() {
        Task3 t3 = new Task3();
        var result = t3.freqDict(new String[] {"this", "and", "that", "and"});

        var output = new HashMap<String, Integer>();
        output.put("this", 1);
        output.put("that", 1);
        output.put("and", 2);

        assertThat(result).isEqualTo(output);
    }
}
