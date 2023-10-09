package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    Task5 task5 = new Task5();

    @Test @DisplayName("Однозначное") void shortInputIsPalindrome() {
        long parent = 0L;
        boolean answer = false;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Палиндром") void inputIsPalindrome() {
        long parent = 1221L;
        boolean answer = true;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Не палиндром двузначное") void inputTwoIsNotPalindrome() {
        long parent = 12L;
        boolean answer = false;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Не палиндром") void inputAndDescIsNotPalindrome() {
        long parent = 1000L;
        boolean answer = false;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Наследник палиндром") void descIsPalindrome() {
        long parent = 123312L;
        boolean answer = true;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }

    @Test @DisplayName("Наследник  не палиндром") void descIsNotPalindrome() {
        long parent = 123322L;
        boolean answer = false;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isEqualTo(answer);
    }
}
