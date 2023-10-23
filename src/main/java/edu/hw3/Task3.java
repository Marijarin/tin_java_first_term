package edu.hw3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {
    <T> HashMap<T, Integer> freqDict(T[] ts) {
        HashMap<T, Integer> freqDict = new HashMap<>(Map.of());
        for (T t : ts) {
            if (!freqDict.containsKey(t)) {
                freqDict.put(t, 1);
            } else {
                int k = freqDict.get(t) + 1;
                freqDict.put(t, k);
            }
        }
        return freqDict;
    }
}
