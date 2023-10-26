package edu.project1.result;

import org.jetbrains.annotations.NotNull;

sealed public interface GuessResult permits Defeat, ErrorResult, FailedGuess, SuccessfulGuess, Win {
    String state();

    int attempt();

    int maxAttempts();

    @NotNull String message();
}




