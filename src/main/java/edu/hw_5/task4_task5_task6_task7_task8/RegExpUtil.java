package edu.hw_5.task4_task5_task6_task7_task8;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {

    //#4
    public boolean checkPW(String toCheck) {
        Pattern special = Pattern.compile("[~!@#$%^&*|]");
        Matcher hasSpecial = special.matcher(toCheck);
        return hasSpecial.find();
    }

    //#5
    public boolean checkCarCode(String toCheck) {
        Pattern code = Pattern.compile("^(\\p{IsAlphabetic})(\\d{3})(\\p{IsAlphabetic}{2})(\\d{3})$");
        Matcher isCode = code.matcher(toCheck);
        return isCode.find();
    }

    //#6
    public boolean isSubstring(String small, String big) {
        Pattern badPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = badPattern.matcher(small);
        Pattern pattern;
        if (matcher.find()) {
            StringBuilder sb = new StringBuilder();
            for (char c : small.toCharArray()) {
                String c1 = String.valueOf(c);
                Matcher matcher1 = badPattern.matcher(c1);
                if (matcher1.find()) {
                    sb.append("\\").append(c);
                } else {
                    sb.append(c);
                }
            }
            String smallChecked = sb.toString();
            pattern = Pattern.compile(smallChecked);
        } else {
            pattern = Pattern.compile(small);
        }
        return pattern.matcher(big).find();
    }
}
