package edu.hw3.task3;

import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public <T> Map<T, Integer> freqDict(T[] ts) {
        HashMap<T, Integer> freqDict = new HashMap<>();
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
