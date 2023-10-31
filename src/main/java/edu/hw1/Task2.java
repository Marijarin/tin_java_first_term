package edu.hw1;

public class Task2 {
    @SuppressWarnings("MagicNumber")
    int countDigits(long num1) {
        int counter = 1;
        long num = Math.abs(num1);
        while ((num - num % 10) >= 1) {
            num = num / 10;
            counter++;
        }
        return counter;
    }
}
