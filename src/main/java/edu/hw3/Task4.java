package edu.hw3;

import java.util.LinkedHashMap;

public class Task4 {
    @SuppressWarnings("MagicNumber")
    public String convertToRoman(int input) {
        int toDo = input;
        if (toDo <= 0) {
            return "Can not be converted!";
        }
        LinkedHashMap<String, Integer> romans = new LinkedHashMap<>();
        romans.put("M", 1000);
        romans.put("CM", 900);
        romans.put("D", 500);
        romans.put("CD", 400);
        romans.put("C", 100);
        romans.put("XC", 90);
        romans.put("L", 50);
        romans.put("XL", 40);
        romans.put("X", 10);
        romans.put("IX", 9);
        romans.put("V", 5);
        romans.put("IV", 4);
        romans.put("I", 1);
        StringBuilder sb;
        sb = new StringBuilder();
        for (var entry : romans.entrySet()) {
            int matches = toDo / entry.getValue();
            if (matches > 0) {
                sb.repeat(entry.getKey(), matches);
            }
            toDo = toDo % entry.getValue();
        }
        return sb.toString();
    }
}
