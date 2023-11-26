package edu.hw_8.Task3;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"MagicNumber", "MultipleStringLiterals"})
public class DecoderThreadsStats {
    private final int[] numberOfThreads = {1, 2, 5, 8, 16, 32};
    private final List<MD5Decoder> decoders = new ArrayList<>(numberOfThreads.length);
    final long[] timeInS = new long[numberOfThreads.length];

    void makeThreadsStats() {
        for (int i = 0; i < numberOfThreads.length; i++) {
            decoders.add(new MD5Decoder());
        }
        for (int k = 0; k < decoders.size(); k++) {
            long start = System.nanoTime() / 1_000_000_000;
            decoders.get(k).nextPasswordManyThreads(numberOfThreads[k]);
            long end = System.nanoTime() / 1_000_000_000;
            timeInS[k] = end - start;
        }

    }

    public String showThreadsStats() {
        makeThreadsStats();
        var sb = new StringBuilder();
        sb.append("""
            Кол-во потоков     Время выполнения, с
                        """);
        sb.append("\n");
        for (int i = 0; i < numberOfThreads.length; i++) {
            sb.append(String.format("%-10s", numberOfThreads[i]));
            sb.append("         ");
            sb.append(String.format("%-18s", timeInS[i]));
            sb.append("         ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
