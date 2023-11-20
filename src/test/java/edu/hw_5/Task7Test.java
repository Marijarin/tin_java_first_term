package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task7Test {
    @Test
    void matchesFirstPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "100";

        var result = regExpUtil.isThisPattern(check, regExpUtil.notLess3ThirdZero);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotFirstPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "101";

        var result = regExpUtil.isThisPattern(check, regExpUtil.notLess3ThirdZero);

        assertThat(result).isFalse();
    }

    @Test
    void matchesSecondPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "100000000000000000000000001";

        var result = regExpUtil.isThisPattern(check, regExpUtil.startAndEndSame);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotSecondPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "1010000000000000000000000000000000000000";

        var result = regExpUtil.isThisPattern(check, regExpUtil.startAndEndSame);

        assertThat(result).isFalse();
    }

    @Test
    void matchesThirdPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "10";

        var result = regExpUtil.isThisPattern(check, regExpUtil.lengthNotLess1NotMore3);

        assertThat(result).isTrue();
    }

    @Test
    void matchesNotThirdPattern() {
        RegExpUtil regExpUtil = new RegExpUtil();
        String check = "*";

        var result = regExpUtil.isThisPattern(check, regExpUtil.lengthNotLess1NotMore3);

        assertThat(result).isFalse();
    }
}
