package edu.hw1;

import java.util.Arrays;

@SuppressWarnings("MagicNumber")
public class Task6 {
    private int counter = 0;

    int countKSteps(int fourDigit) {
        int fLength = String.valueOf(fourDigit).chars().distinct().toArray().length;
        final int downL = 1000;
        final int upL = 9999;
        if (fourDigit < downL || fourDigit > upL || fLength == 1) {
            return -1;
        } else {
            return fK(fourDigit);
        }
    }

    private int fK(int intoK) {
        final int k = 6174;
        if (intoK == k) {
            return counter;
        }
        int intoKOnceMore = intoK;
        final int digits = 4;
        int[] ints = new int[digits];
        int c = 1000;
        for (int i = 0; i < digits; i++) {
            ints[i] = intoKOnceMore / c;
            intoKOnceMore -= (intoKOnceMore / c) * c;
            c /= 10;
        }
        int[] sorted = Arrays.stream(ints).sorted().toArray();
        int ascNumber = 0;
        int descNumber = 0;
        c = 1000;
        for (int i = 0; i < digits; i++) {
            ascNumber += sorted[i] * c;
            c /= 10;
        }
        c = 1000;
        for (int i = digits - 1; i >= 0; i--) {
            descNumber += sorted[i] * c;
            c /= 10;
        }
        int diff = Math.abs(ascNumber - descNumber);
        if (diff != k) {
            fK(diff);
        }
        counter++;
        return counter;
    }

}
