package edu.hw_5.task1_task2;

public enum DateConstants {
    FRIDAY(5), THIS_DAY(13), MONTHS_COUNT(12);
    private final int number;

    DateConstants(int number) {
        this.number = number;
    }

    int valueOf() {
        return number;
    }
}
