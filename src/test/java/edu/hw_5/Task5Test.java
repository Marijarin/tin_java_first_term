package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void isCode(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "А123ВЕ777";

        var result = regExpUtil.checkCarCode(toCheck);

        assertThat(result).isTrue();
    }
    @Test
    void isNotCode1(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "А123ВЕ7777";

        var result = regExpUtil.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }

    @Test
    void isNotCode2(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "А123ВГ77";

        var result = regExpUtil.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }

    @Test
    void isNotCode3(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String toCheck = "123АВЕ777";

        var result = regExpUtil.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }
}
