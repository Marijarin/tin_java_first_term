package edu.hw3;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Task2 {
    public List<String> clusterize(String brackets) {
        List<String> clusters = new ArrayList<>();
        Deque<Character> openBrackets = new ArrayDeque<>();
        var sb = new StringBuilder();
        var warn = "Current cluster is not balanced!";
        for (int i = 0; i < brackets.length(); i++) {
            char c = brackets.charAt(i);
            switch (c) {
                case '(' -> {
                    openBrackets.add(c);
                    sb.append(c);
                }
                case ')' -> {
                    if (!openBrackets.isEmpty()) {
                        openBrackets.pop();
                        sb.append(c);
                    } else {
                        sb.append(warn);
                    }
                }
                default -> {
                    sb.append(c);
                    continue;
                }
            }
            if (openBrackets.isEmpty()) {
                clusters.add(sb.toString());
                sb.delete(0, sb.length());
            }
        }
        if (!openBrackets.isEmpty()) {
            clusters.add(warn);
        }
        return clusters;
    }
}
