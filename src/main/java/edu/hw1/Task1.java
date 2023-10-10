package edu.hw1;

import java.util.Arrays;

public class Task1 {

    int minutesToSeconds(String vLength) throws RuntimeException {
        final int secLimit = 60;
        final int minLimit = Integer.MAX_VALUE/secLimit;
        int[] minAndSec;
        try {
            minAndSec =
                Arrays.stream(vLength.split(":"))
                    .map(String::trim)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        } catch (RuntimeException e) {
            return -1;
        }
        int answer = -1;

        if (minAndSec[1] < secLimit && minAndSec[0] >= 0 && minAndSec[1] >= 0 && minAndSec[0] < minLimit) {
            answer = minAndSec[0] * secLimit + minAndSec[1];
        }
        return answer;
    }
}
