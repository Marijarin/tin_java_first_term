package edu.hw_9.task1;

import java.util.Arrays;

public class PTask {
    final double[] data;

    public PTask(double[] data) {
        this.data = data;
    }

    @Override public String toString() {
        return Arrays.toString(this.data);
    }
}
