package edu.project1;

import org.jetbrains.annotations.NotNull;

sealed public interface GuessResult {
    String state();

    int attempt();

    int maxAttempts();

    @NotNull String message();

    record Defeat(int currentAttempt, int maxAttempt) implements GuessResult {
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

    record Win(int currentAttempt, int maxAttempt, String word) implements GuessResult {
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

    record SuccessfulGuess(int attempt, String word) implements GuessResult {
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
            return "Hit!\nThe word is: " + word;
        }
    }

    record FailedGuess(int attempt, int maxAttempt, String word) implements GuessResult {
        @Override
        public String state() {
            return null;
        }

        @Override
        public int maxAttempts() {
            return maxAttempt;
        }

        @Override
        public @NotNull String message() {
            return "Missed, mistake " + attempt + " out of " + maxAttempt + ".\nThe word is: " + word;
        }
    }

    record Error(String word) implements GuessResult {

        @Override
        public String state() {
            return word;
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
            return "Oh no, the word is bad. It is: " + word + " Please, rerun main";
        }
    }
}
