package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    void firstPattern() {
        String check = "001";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(0));

        assertThat(result).isTrue();
    }

    @Test
    void firstPatternNot() {
        String check = "0010";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(0));

        assertThat(result).isFalse();
    }

    @Test
    void secondPattern1() {
        String check = "001";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(1));

        assertThat(result).isTrue();
    }

    @Test
    void secondPattern1Not() {
        String check = "0010";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(1));

        assertThat(result).isFalse();
    }

    @Test
    void secondPattern2() {
        String check = "1101";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(1));

        assertThat(result).isTrue();
    }

    @Test
    void secondPattern2Not() {
        String check = "10010";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(1));

        assertThat(result).isFalse();
    }

    @Test
    void thirdPattern() {
        String check = "10010";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(2));

        assertThat(result).isTrue();
    }

    @Test
    void thirdPatternNot() {
        String check = "100100";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(2));

        assertThat(result).isFalse();
    }

    @Test
    void fourthPattern() {
        String check = "100100";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(3));

        assertThat(result).isTrue();
    }

    @Test
    void fourthPatternNot1() {
        String check = "111";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(3));

        assertThat(result).isFalse();
    }

    @Test
    void fourthPatternNot2() {
        String check = "11";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(3));

        assertThat(result).isFalse();
    }

    @Test
    void fifthPattern() {
        String check = "1111111";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(4));

        assertThat(result).isTrue();
    }

    @Test
    void fifthPatternNot() {
        String check = "0111111";

        RegExpUtil r = new RegExpUtil();

        var result = r.isThisPattern(check, r.others.get(4));

        assertThat(result).isFalse();
    }

}
