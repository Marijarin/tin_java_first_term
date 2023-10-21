package edu.project1.result;

import org.jetbrains.annotations.NotNull;

public record Defeat(int currentAttempt, int maxAttempt) implements GuessResult {
    @Override
    public String state() {
        return null;
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
        if (currentAttempt <= maxAttempt) {
            return "Game over";
        } else {
            return "You lost!";
        }
    }
}
