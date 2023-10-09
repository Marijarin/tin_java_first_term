package edu.hw1;

import java.util.Arrays;

public class Task3 {
    boolean isNestable(int[] into, int[] outer) {
        if (into.length == 0) {
            return true;
        } else if ( outer.length == 0) {
            return false;
        }
        return Arrays.stream(into).max().getAsInt() < Arrays.stream(outer).max().getAsInt()
                && Arrays.stream(into).min().getAsInt() > Arrays.stream(outer).min().getAsInt();
    }
}
