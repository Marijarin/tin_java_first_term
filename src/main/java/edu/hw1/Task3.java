package edu.hw1;

import java.util.Arrays;
import java.util.Collections;

class Task3 {
    static boolean isNestable(int[] into, int[] outer) {
        if (into.length == 0 || outer.length == 0) {
            return true;
        }
        if (
            Arrays.stream(into).max().getAsInt() < Arrays.stream(outer).max().getAsInt()
                && Arrays.stream(into).min().getAsInt() > Arrays.stream(outer).min().getAsInt()
        ) {
            return true;
        } else {
           return false;
        }
    }
}
