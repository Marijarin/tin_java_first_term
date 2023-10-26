package edu.hw3.task1;

import java.util.HashMap;
import java.util.Map;

public class Task1 {
    public String atbash(String source) {
        StringBuilder sb = new StringBuilder();
        String alphabetLow = "abcdefghijklmnopqrstuvwxyz";
        int l = alphabetLow.length();
        String alphabetHigh = alphabetLow.toUpperCase();
        String reversedAlphabetLow = makeReverse(sb, alphabetLow);
        String reversedAlphabetHigh = makeReverse(sb, alphabetHigh);
        Map<Character, Character> cipherMap = new HashMap<>(Map.of());
        for (int i = 0; i < l; i++) {
            cipherMap.put(alphabetLow.charAt(i), reversedAlphabetLow.charAt(i));
        }
        for (int i = 0; i < l; i++) {
            cipherMap.put(alphabetHigh.charAt(i), reversedAlphabetHigh.charAt(i));
        }
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            Character ciphered = cipherMap.get(c);
            if (ciphered != null) {
                sb.append(ciphered.charValue());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String makeReverse(StringBuilder sb, String toDo) {
        sb.append(toDo);
        sb.reverse();
        String reversed = sb.toString();
        sb.delete(0, toDo.length());
        return reversed;
    }
}

