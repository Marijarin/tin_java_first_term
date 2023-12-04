package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task4Test {

    @Test
    void hasSpecial(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "alfkj12334400-fknalfknwefn#";

        var result = regExpUtil.checkPW(toCheck);

        assertThat(result).isTrue();
    }
    @Test
    void hasNotSpecial(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "alfkjalfknalalfkj12334400-";

        var result = regExpUtil.checkPW(toCheck);

        assertThat(result).isFalse();
    }
}
