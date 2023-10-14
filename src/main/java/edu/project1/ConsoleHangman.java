package edu.project1;

import org.jetbrains.annotations.NotNull;
import java.util.Arrays;
import java.util.List;

class ConsoleHangman implements Dictionary {
    private final List<String> words = Arrays.asList(
        "scratch",
        "zipper",
        "byte",
        "jelly",
        "ivy"
    );

    @Override
    public @NotNull String randomWord() {
        int random = (int) (Math.random() * words.size());
        return words.get(random);
    }

    public void run() {
        while (true) {
            // ...
        }
    }

    /*private GuessResult tryGuess(Session session, String input) {
    }

    private void printState(GuessResult guess) {
    }*/
}
