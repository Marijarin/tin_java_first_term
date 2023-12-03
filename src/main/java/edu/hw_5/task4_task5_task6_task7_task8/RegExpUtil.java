package edu.hw_5.task4_task5_task6_task7_task8;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegExpUtil {
    /**
     * Регулярные выражения для бинарного алфавита
     * Первое парсит строку: содержит не менее 3 символов, причем третий символ равен 0
     * Второе парсит строку: начинается и заканчивается одним и тем же символом
     * Третье парсит строку: длина не менее 1 и не более 3
     **/
    public final Pattern notLess3ThirdZero = Pattern.compile("^([0,1]{2})(0)");
    public final Pattern startAndEndSame = Pattern.compile("^(?<start>[0,1])([0,1]*)(\\k<start>)$");
    public final Pattern lengthNotLess1NotMore3 = Pattern.compile("^[0,1][^*]{1,3}$");
    /**
     * * Регулярные выражения для бинарного алфавита, парсят строки из алфавита {0, 1} по следующим условиям:
     * [0] нечетной длины
     * [1] начинается с 0 и имеет нечетную длину, или начинается с 1 и имеет четную длину
     * [2] количество 0 кратно 3
     * [3]любая строка, кроме 11 или 111
     * [4] каждый нечетный символ равен 1
     * [5] содержит не менее двух 0 и не более одной 1
     * [6] нет последовательных 1
     **/
    public final List<Pattern> others = List.of(
        Pattern.compile("^([0,1])(?:[0,1]{2})*?$"),
        Pattern.compile("^(0)(?:[0,1]{2})*?$|^(1)([0,1])(?:[0,1]{2})*?$"),
        Pattern.compile("^((1*)(0)(1*)(0)(1*)(0)(1*))+?$"),
        Pattern.compile("^(?!111$)[0,1]*^(?!11$)[0,1]*"),
        Pattern.compile("^(1)(?:[0,1](1))*?[0,1]?$"),
        Pattern.compile("^(?:0+10+|100+|00+1)$"),
        Pattern.compile("^(?:(?!11)[01])+$")
    );
    /**
     * *
     * Регулярное выражение, которое парсит строку со специальными символами.
     */
    public final Pattern special = Pattern.compile("[~!@#$%^&*|]");
    /**
     * Регулярное выражение для валидации российских номерных знаков типа А123ВЕ777.
     **/
    public final Pattern code = Pattern.compile("^(\\p{IsAlphabetic})(\\d{3})(\\p{IsAlphabetic}{2})(\\d{3})$");

    /**
     * Регулярное выражение, которое парсит строку, в которой есть символы не из латинского алфавита и не цифры.
     **/

    public final Pattern badPattern = Pattern.compile("[^a-zA-Z0-9]");

    //#4
    public boolean checkPW(String toCheck) {
        Matcher hasSpecial = special.matcher(toCheck);
        return hasSpecial.find();
    }

    //#5
    public boolean checkCarCode(String toCheck) {
        Matcher isCode = code.matcher(toCheck);
        return isCode.find();
    }

    //#6
    public boolean isSubstring(String small, String big) {
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
