package edu.project1.result;

import org.jetbrains.annotations.NotNull;

public record ErrorResult(String error) implements GuessResult {
    @Override
    public String state() {
        return error;
    }

    @Override
    public int attempt() {
        return 0;
    }

    @Override
    public int maxAttempts() {
        return 0;
    }

    @Override
    public @NotNull String message() {
        return "Oh no, the word is bad. It is: " + state() + " Please, rerun main";
    }
}
