package edu.hw_5;

import edu.hw_5.task4_task5_task6_task7_task8.RegExpUtil;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
public class Task6Test {
    @Test
    void isSubstringWithSpecials(){
        RegExpUtil regExpUtil = new RegExpUtil();
        String small = "ap*le";
        String big = "appleapple";

        var result = regExpUtil.isSubstring(small, big);

        assertThat(result).isFalse();
    }
}
