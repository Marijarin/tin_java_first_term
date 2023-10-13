package edu.hw1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    Task5 task5 = new Task5();

    @Test
    @DisplayName("Однозначное")
    void shortInputIsPalindrome() {
        long parent = 0L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Палиндром")
    void inputIsPalindrome() {
        long parent = 1221L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Не палиндром двузначное")
    void inputTwoIsNotPalindrome() {
        long parent = 12L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Не палиндром")
    void inputAndDescIsNotPalindrome() {
        long parent = 1000L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isFalse();
    }

    @Test
    @DisplayName("Наследник палиндром")
    void descIsPalindrome() {
        long parent = 123312L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isTrue();
    }

    @Test
    @DisplayName("Наследник  не палиндром")
    void descIsNotPalindrome() {
        long parent = 123322L;
        boolean result = task5.isPalindromeDescendant(parent);
        assertThat(result).isFalse();
    }
}
