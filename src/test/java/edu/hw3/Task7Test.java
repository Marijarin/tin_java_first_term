package edu.hw3;

import edu.hw3.task7.NullComparator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Comparator;
import java.util.TreeMap;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {

    @Test
    @DisplayName("TreeMap with null last")
    void checkLastKey() {
        TreeMap<String, String> tm = new TreeMap<>(new NullComparator<>(Comparator.naturalOrder()));
        tm.put(null, null);
        tm.put("am", "am");
        tm.put(null, "ff");
        tm.put("bb", "ff");

        String result = tm.lastKey();

        assertThat(result).isEqualTo("bb");
    }

    @Test
    @DisplayName("TreeMap with null first")
    void checkFirstKey() {
        TreeMap<String, String> tm = new TreeMap<>(new NullComparator<>(Comparator.naturalOrder()));
        tm.put(null, null);
        tm.put("am", "am");
        tm.put(null, "ff");
        tm.put("bb", "ff");

        String result = tm.firstKey();

        assertThat(result).isEqualTo(null);
    }
}
