package edu.project1.result;

import org.jetbrains.annotations.NotNull;

public record FailedGuess(int attempt, int maxAttempt, String word) implements GuessResult {
    @Override
    public String state() {
        return word;
    }

    @Override
    public int maxAttempts() {
        return maxAttempt;
    }

    @Override
    public @NotNull String message() {
        return "Missed, mistake " + attempt + " out of " + maxAttempt + ".\nThe word is: " + state();
    }
}
