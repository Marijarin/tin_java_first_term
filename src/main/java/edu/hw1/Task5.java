package edu.hw1;

import java.util.ArrayList;

public class Task5 {

    boolean isPalindromeDescendant(long parent) {
        String parentStr = String.valueOf(parent);

        return switch (parentStr.length()) {
            case 1 -> false;
            case 2 -> parentStr.charAt(0) == parentStr.charAt(1);
            default -> {
                String parentStrFromEnd = headToLegs(parentStr);
                yield parentStrFromEnd.equals(parentStr)
                    || (checkDescForPalindrome(parent) && parentStrFromEnd.charAt(0) != '0');
            }
        };
    }

    private String headToLegs(String toDo) {
        StringBuilder sb = new StringBuilder();
        for (int i = toDo.length() - 1; i >= 0; i--) {
            sb.append(toDo.charAt(i));
        }
        return sb.toString();
    }

    private ArrayList<Integer> makeIntsFromNumber(long number) {
        char[] chars = String.valueOf(number).toCharArray();
        ArrayList<Integer> ints = new ArrayList<>(String.valueOf(number).length());
        for (char c : chars) {
            ints.add(Integer.parseInt(String.valueOf(c)));
        }
        return ints;
    }

    private long makeDescendant(long parent) {
        ArrayList<Integer> intsMade = makeIntsFromNumber(parent);
        long result;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < intsMade.size() - 1; i += 2) {
            String s = String.valueOf(intsMade.get(i) + intsMade.get(i + 1));
            sb.append(s);
        }
        result = Long.parseLong(sb.toString());
        return result;
    }

    private boolean checkDescForPalindrome(long parent) {
        String parentStr = String.valueOf(parent);
        if (parentStr.length() % 2 != 0 || parentStr.length() < 2) {
            return false;
        } else {
            long made = makeDescendant(parent);
            String fromMade = String.valueOf(made);
            String legsFirst = headToLegs(fromMade);
            if (fromMade.equals(legsFirst)) {
                return true;
            } else {
                return checkDescForPalindrome(made);
            }
        }
    }

}
