package edu.hw1;

public class Task2 {
    int countDigits(long num1) {
        int counter = 1;
        final int ten = 10;
        long num = Math.abs(num1);
        while ((num - num % ten) >= 1) {
            num = num / ten;
            counter++;
        }
        return counter;
    }
}
