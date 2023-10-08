package edu.hw1;

import java.util.Arrays;

public class Task3 {
    boolean isNestable(int[] into, int[] outer) {
        if (into.length == 0 || outer.length == 0) {
            return true;
        }
        return Arrays.stream(into).max().getAsInt() < Arrays.stream(outer).max().getAsInt()
                && Arrays.stream(into).min().getAsInt() > Arrays.stream(outer).min().getAsInt();
    }
}
