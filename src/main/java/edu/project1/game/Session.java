package edu.project1.game;

import edu.project1.result.Defeat;
import edu.project1.result.ErrorResult;
import edu.project1.result.FailedGuess;
import edu.project1.result.GuessResult;
import edu.project1.result.SuccessfulGuess;
import edu.project1.result.Win;
import edu.project1.util.Dictionary;
import edu.project1.util.DictionaryImpl;
import edu.project1.util.Printable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class Session implements Printable {
    private final String puzzle;
    private final int maxAttempts = 5;

    public Session(int level) {
        Dictionary dictionary = new DictionaryImpl();
        this.puzzle = dictionary.randomWord(level);
    }

    public GuessResult startGame() {
        if (puzzle.isEmpty() || puzzle.length() < 2) {
            GuessResult g = new ErrorResult(puzzle);
            LOGGER.info(g.message());
            return g;
        } else {
            LOGGER.info(
                "\nYou can make only "
                    + maxAttempts
                    +
                    " mistakes.\nIf you want to finish the game, please, print end or empty space in separate line.\n");
            String response = "*".repeat(puzzle.length());
            return proceedGame(response);
        }
    }

    @NotNull private GuessResult proceedGame(String response) {
        Scanner sc = new Scanner(System.in);
        int x = 1;
        StringBuilder responseN = new StringBuilder();
        responseN.append(response);
        GuessResult g = new SuccessfulGuess(x, responseN.toString());
        while (x <= maxAttempts && !responseN.toString().equals(puzzle)) {
            LOGGER.info("\nGuess a letter: ");
            String guess;
            try {
                guess = sc.nextLine().trim();
                if (guess.length() != 1) {
                    sc.close();
                    g = giveUp(x);
                    LOGGER.info(g.message());
                    return giveUp(x);
                }
            } catch (NoSuchElementException e) {
                return giveUp(x);
            }
            if (puzzle.contains(guess) && !responseN.toString().contains(guess)) {
                for (int i = 0; i < puzzle.length(); i++) {
                    if (puzzle.charAt(i) == guess.toCharArray()[0]) {
                        responseN.replace(i, i + 1, guess);
                    }
                }
                g = new SuccessfulGuess(x, responseN.toString());
                LOGGER.info(g.message());
                if (g.state().equals(puzzle)) {
                    g = new Win(x, maxAttempts, puzzle);
                    LOGGER.info(g.message());
                    sc.close();
                }
            } else {
                x++;
                g = new FailedGuess(x, maxAttempts, responseN.toString());
                LOGGER.info(g.message());
            }
        }
        if (x > maxAttempts) {
            sc.close();
            g = new Defeat(x, maxAttempts);
            LOGGER.info(g.message());
        }
        return g;
    }

    @NotNull GuessResult giveUp(int currentAttempt) {
        return new Defeat(currentAttempt, maxAttempts);
    }
}
