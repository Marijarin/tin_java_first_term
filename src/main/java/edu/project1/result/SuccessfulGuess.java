package edu.project1.result;

import org.jetbrains.annotations.NotNull;

public record SuccessfulGuess(int attempt, String word) implements GuessResult {
    @Override
    public String state() {
        return word;
    }

    @Override
    public int maxAttempts() {
        return 0;
    }

    @Override
    public @NotNull String message() {
        return "Hit!\nThe word is: " + state();
    }
}

