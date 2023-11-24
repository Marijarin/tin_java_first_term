package edu.hw_7;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class PiCounterStats {
    private final int[] numberOfThreads = {1, 2, 5, 10, 20, 50};
    private final List<PiCounter> piCounters = new ArrayList<>(numberOfThreads.length);
    final long[] timeInS = new long[numberOfThreads.length];
    final double[] pis = new double[numberOfThreads.length];

    void makeThreadsStats() {
        int totalDots = 1_000_000;
        for (int numberOfThread : numberOfThreads) {
            piCounters.add(new PiCounter(totalDots, numberOfThread));
        }
        for (int k = 0; k < piCounters.size(); k++) {
            long start = System.nanoTime() / 1_000_000;
            pis[k] = piCounters.get(k).countPiManyThreads();
            long end = System.nanoTime() / 1_000_000;
            timeInS[k] = end - start;
        }

    }

    String showThreadsStats() {
        makeThreadsStats();
        var sb = new StringBuilder();
        sb.append("""
            Кол-во потоков     Время выполнения, с      PI
                        """);
        sb.append("\n");
        for (int i = 0; i < numberOfThreads.length; i++) {
            sb.append(String.format("%-10s", numberOfThreads[i]));
            sb.append("         ");
            sb.append(String.format("%-18s", timeInS[i]));
            sb.append("         ");
            sb.append(String.format("%-20s", pis[i]));
            sb.append("\n");
        }
        return sb.toString();
    }
}
