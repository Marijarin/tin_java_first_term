package edu.project1.result;

import org.jetbrains.annotations.NotNull;

public record Win(int currentAttempt, int maxAttempt, String word) implements GuessResult {
    @Override
    public String state() {
        return word;
    }

    @Override
    public int attempt() {
        return currentAttempt;
    }

    @Override
    public int maxAttempts() {
        return maxAttempt;
    }

    @Override
    public @NotNull String message() {
        return "You won!";
    }
}
