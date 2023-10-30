package edu.hw3;

import edu.hw3.task8.BackwardIterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Task8Test {
    @Test
    @DisplayName("Back iterator for ints")
    void intsIterator() {
        List<Integer> ints = List.of(1, 2, 3);
        Iterator<Integer> i = new BackwardIterator<>(ints);

        List<Integer> result = new ArrayList<>();
        result.add(i.next());
        result.add(i.next());
        result.add(i.next());
        List<Integer> output = List.of(3, 2, 1);

        assertThat(result).isEqualTo(output);
    }

    @Test
    @DisplayName("Back iterator for ints has not next")
    void intsIteratorThrows() {
        List<Integer> ints = List.of(1, 2, 3);
        Iterator<Integer> i = new BackwardIterator<>(ints);

        List<Integer> result = new ArrayList<>();
        result.add(i.next());
        result.add(i.next());
        result.add(i.next());

        assertThatThrownBy(() -> {
            result.add(i.next());
        }).isInstanceOf(NoSuchElementException.class);
    }
}
