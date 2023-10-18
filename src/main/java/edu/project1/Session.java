package edu.project1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

class Session {
    private final String puzzle;
    private final int maxAttempts = 5;
    private final static Logger LOGGER = LogManager.getLogger();
    private final String toGiveUp = "end";

    Session() {
        Dictionary dictionary = new Dictionary();
        this.puzzle = dictionary.randomWord();
    }

    GuessResult startGame() {
        if (puzzle.isEmpty() || puzzle.length() < 2) {
            GuessResult g = new GuessResult.Error(puzzle);
            LOGGER.info(g.message());
            return g;
        } else {
            LOGGER.info(
                "\nYou can make only "
                    + maxAttempts
                    + " mistakes.\nIf you want to finish the game, please, print "
                    + toGiveUp
                    + " or empty space in separate line.\n");
            String response = "*".repeat(puzzle.length());
            return proceedGame(response);
        }
    }

    @NotNull private GuessResult proceedGame(String response) {
        Scanner sc = new Scanner(System.in);
        int x = 1;
        StringBuilder responseN = new StringBuilder();
        responseN.append(response);
        GuessResult g = new GuessResult.SuccessfulGuess(x, responseN.toString());
        while (x <= maxAttempts && !responseN.toString().equals(puzzle)) {
            LOGGER.info("\nGuess a letter: ");
            String guess = String.valueOf(sc.nextLine().trim().charAt(0));
            if (guess.equals(toGiveUp) || guess.isEmpty()) {
                sc.close();
                g = giveUp(x);
                LOGGER.info(g.message());
                return giveUp(x);
            }
            if (puzzle.contains(guess) && !responseN.toString().contains(guess)) {
                for (int i = 0; i < puzzle.length(); i++) {
                    if (puzzle.charAt(i) == guess.toCharArray()[0]) {
                        responseN.replace(i, i + 1, guess);
                    }
                }
                g = new GuessResult.SuccessfulGuess(x, responseN.toString());
                LOGGER.info(g.message());
                if (g.state().equals(puzzle)) {
                    g = new GuessResult.Win(x, maxAttempts, puzzle);
                    LOGGER.info(g.message());
                    sc.close();
                }
            } else {
                x++;
                g = new GuessResult.FailedGuess(x, maxAttempts, responseN.toString());
                LOGGER.info(g.message());
            }
        }
        if (x > maxAttempts) {
            sc.close();
            g = new GuessResult.Defeat(x, maxAttempts);
            LOGGER.info(g.message());
        }
        return g;
    }

    @NotNull GuessResult giveUp(int currentAttempt) {
        return new GuessResult.Defeat(currentAttempt, maxAttempts);
    }

    private static class Dictionary implements edu.project1.Dictionary {
        private final List<String> words = Arrays.asList(
            "scratch",
            "zipper",
            "kilobyte",
            "jelly",
            "ivy",
            "peekaboo"
        );

        public @NotNull String randomWord() {
            int random = (int) (Math.random() * words.size());
            return words.get(random);
        }
    }
}
