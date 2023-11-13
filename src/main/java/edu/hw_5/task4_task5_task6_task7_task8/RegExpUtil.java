package edu.hw_5.task4_task5_task6_task7_task8;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {

    public final Pattern first = Pattern.compile("^([0,1][^*]{2})(0)");
    public final Pattern second = Pattern.compile("^(?<start>[0,1][^*])([^*][0,1]*)(\\k<start>)$");
    public final Pattern third = Pattern.compile("^[0,1][^*]{1,3}$");

    public final List<Pattern> others = List.of(
        Pattern.compile("^([0,1])(?:[0,1]{2})*?$"),
        Pattern.compile("^(0)(?:[0,1]{2})*?$|^(1)([0,1])(?:[0,1]{2})*?$"),
        Pattern.compile("^((1*)(0)(1*)(0)(1*)(0)(1*))+?$"),
        Pattern.compile("^(?!111$)[0,1]*^(?!11$)[0,1]*"),
        Pattern.compile("^(1)(?:[0,1](1))*?[0,1]?$")
    );

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
            pattern = Pattern.compile(slashedSpecialsString(small, badPattern));
        } else {
            pattern = Pattern.compile(small);
        }
        return pattern.matcher(big).find();
    }

    private String slashedSpecialsString(String small, Pattern badPattern) {
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
        return sb.toString();
    }

    //#7
    public boolean isThisPattern(String bin, Pattern pattern) {
        Matcher m = pattern.matcher(bin);
        return m.matches();
    }
}
