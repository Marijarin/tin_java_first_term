package edu.hw1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Task4 {
    static String fixString(String broken) {
        List<Character> brokenList = new ArrayList<>();
        for (int i = 0; i < broken.length(); i++) {
            brokenList.add(broken.toCharArray()[i]);
        }
        StringBuilder sb = new StringBuilder();
        int n = brokenList.size();
        for (int i = 0; i < n; i+=2) {
            if (i < n - 1) {
                Collections.swap(brokenList, i, i + 1);
                sb.append(brokenList.get(i)).append(brokenList.get(i+1));
            }
        }
        return sb.toString();
    }
}
