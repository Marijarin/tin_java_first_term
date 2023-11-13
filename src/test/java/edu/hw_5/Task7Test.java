package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void matchesFirstPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "100";

        var result = r.isThisPattern(check, r.first);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotFirstPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "101";

        var result = r.isThisPattern(check, r.first);

        assertThat(result).isFalse();
    }

    @Test
    void matchesSecondPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "100000000000000000000000001";

        var result = r.isThisPattern(check, r.second);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotSecondPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "1010000000000000000000000000000000000000";

        var result = r.isThisPattern(check, r.second);

        assertThat(result).isFalse();
    }

    @Test
    void matchesThirdPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "10";

        var result = r.isThisPattern(check, r.third);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotThirdPattern() {
        RegExpUtil r = new RegExpUtil();
        String check = "*";

        var result = r.isThisPattern(check, r.third);

        assertThat(result).isFalse();
    }
}
