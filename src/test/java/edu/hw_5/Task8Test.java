package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task8Test {
    @Test
    void firstPattern() {
        String check = "001";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(0));

        assertThat(result).isTrue();
    }

    @Test
    void firstPatternNot() {
        String check = "0010";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(0));

        assertThat(result).isFalse();
    }

    @Test
    void secondPattern1() {
        String check = "001";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(1));

        assertThat(result).isTrue();
    }

    @Test
    void secondPattern1Not() {
        String check = "0010";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(1));

        assertThat(result).isFalse();
    }

    @Test
    void secondPattern2() {
        String check = "1101";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(1));

        assertThat(result).isTrue();
    }

    @Test
    void secondPattern2Not() {
        String check = "10010";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(1));

        assertThat(result).isFalse();
    }

    @Test
    void thirdPattern() {
        String check = "10010";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(2));

        assertThat(result).isTrue();
    }

    @Test
    void thirdPatternNot() {
        String check = "100100";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(2));

        assertThat(result).isFalse();
    }

    @Test
    void fourthPattern() {
        String check = "100100";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(3));

        assertThat(result).isTrue();
    }

    @Test
    void fourthPatternNot1() {
        String check = "111";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(3));

        assertThat(result).isFalse();
    }

    @Test
    void fourthPatternNot2() {
        String check = "11";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(3));

        assertThat(result).isFalse();
    }

    @Test
    void fifthPattern() {
        String check = "1111111";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(4));

        assertThat(result).isTrue();
    }

    @Test
    void fifthPatternNot() {
        String check = "0111111";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(4));

        assertThat(result).isFalse();
    }

    @Test
    void sixthPattern() {
        String check = "0100";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(5));

        assertThat(result).isTrue();
    }

    @Test
    void sixthPatternNot() {
        String check = "0101";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(5));

        assertThat(result).isFalse();
    }

    @Test
    void seventhPattern() {
        String check = "101001";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(6));

        assertThat(result).isTrue();
    }

    @Test
    void seventhPatternNot() {
        String check = "01101";

        RegExpUtil regExpUtil = new RegExpUtil();

        var result = regExpUtil.isThisPattern(check, regExpUtil.others.get(6));

        assertThat(result).isFalse();
    }

}
