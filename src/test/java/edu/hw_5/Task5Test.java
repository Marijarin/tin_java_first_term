package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    @Test
    void isCode(){
        RegExpUtil r = new RegExpUtil();
        String toCheck = "А123ВЕ777";

        var result = r.checkCarCode(toCheck);

        assertThat(result).isTrue();
    }
    @Test
    void isNotCode1(){
        RegExpUtil r = new RegExpUtil();
        String toCheck = "А123ВЕ7777";

        var result = r.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }

    @Test
    void isNotCode2(){
        RegExpUtil r = new RegExpUtil();
        String toCheck = "А123ВГ77";

        var result = r.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }

    @Test
    void isNotCode3(){
        RegExpUtil r = new RegExpUtil();
        String toCheck = "123АВЕ777";

        var result = r.checkCarCode(toCheck);

        assertThat(result).isFalse();
    }
}
