package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    @DisplayName("Back iterator for ints")
    void intsIterator(){
        List<Integer> ints = List.of(1, 2, 3, 4, 5, 6);
        Iterator<Integer> i = new BackwardIterator<>(ints);

        List<Integer> result = new ArrayList<>();
        while (i.hasNext()) {
            var element = i.next();
            result.add(element);
        }
        List<Integer> output = List.of(6, 5, 4, 3, 2, 1);

        assertThat(result).isEqualTo(output);
    }
}
