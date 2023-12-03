package edu.project1.game;

import edu.project1.result.Defeat;
import edu.project1.result.ErrorResult;
import edu.project1.result.FailedGuess;
import edu.project1.result.GuessResult;
import edu.project1.result.SuccessfulGuess;
import edu.project1.result.Win;
import edu.project1.util.Printable;
import java.util.NoSuchElementException;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class SessionManager implements Printable {
    private final String puzzle;
    private final int maxAttempts;
    private int attempt = 1;
    private final Scanner sc;
    private GuessResult result;
    Renderer r = new Renderer();

    public SessionManager(String puzzle, int maxAttempts, Scanner sc) {
        this.puzzle = puzzle;
        this.maxAttempts = maxAttempts;
        this.sc = sc;
    }

    public void startGame() {
        if (puzzle.isEmpty() || puzzle.length() < 2) {
            result = new ErrorResult(puzzle);
            LOGGER.info(result.message());
        } else {
            LOGGER.info(
                "\nYou can make only {} mistakes."
                    + "\nIf you want to finish the game, please, print end or empty space in separate line.\n",
                maxAttempts
            );
            r.renderHangman(maxAttempts);
            String response = "*".repeat(puzzle.length());
            GuessResult g = checkGuess(response, attempt);
            proceedGame(g);
        }
    }

    @SuppressWarnings("MultipleStringLiterals")
    public String validateInput() {
        r.letterPrompt();
        String guess;
        try {
            guess = sc.nextLine().trim();
            if (guess.length() != 1 && !guess.equals("end")) {
                r.lose("The end");
                sc.close();
                guess = "";
            }
        } catch (NoSuchElementException e) {
            guess = e.getMessage();
            return guess;
        }
        return guess;
    }

    @SuppressWarnings("MultipleStringLiterals")
    @NotNull public GuessResult checkGuess(String response, int attempt) {
        String input = validateInput();
        switch (input) {
            case "" -> {
                ErrorResult er = new ErrorResult("not valid input");
                r.lose(er.message());
                return er;
            }
            case "end" -> {
                GuessResult resultEnd = giveUp(attempt);
                r.lose(resultEnd.message());
                return resultEnd;
            }
            default -> {
                return processGuess(response, input);
            }
        }
    }

    @NotNull public GuessResult processGuess(String response, String guess) {
        StringBuilder responseN = new StringBuilder();
        GuessResult g;
        responseN.append(response);
        if (puzzle.contains(guess) && !responseN.toString().contains(guess)) {
            for (int i = 0; i < puzzle.length(); i++) {
                if (puzzle.charAt(i) == guess.toCharArray()[0]) {
                    responseN.replace(i, i + 1, guess);
                }
            }
            g = new SuccessfulGuess(attempt - 1, responseN.toString());
            r.renderHangman(maxAttempts - (attempt - 1));
            LOGGER.info(g.message());
            if (g.state().equals(puzzle)) {
                g = new Win(attempt - 1, maxAttempts, puzzle);
                r.win(g.message());
            }
        } else {
            attempt++;
            g = new FailedGuess(attempt - 1, maxAttempts, responseN.toString());
            r.renderHangman(maxAttempts - (attempt - 1));
            LOGGER.info(g.message());

        }
        return g;
    }

    public void proceedGame(GuessResult g) {
        GuessResult intermediate;
        if (g instanceof SuccessfulGuess || g instanceof FailedGuess) {
            if (g.attempt() <= maxAttempts) {
                intermediate = checkGuess(g.state(), g.attempt());
                proceedGame(intermediate);
            } else {
                result = new Defeat(g.attempt(), maxAttempts);
                r.renderHangman(0);
                r.lose(result.message());
            }
        } else {
            result = g;
        }
    }

    public GuessResult getResult() {
        return result;
    }

    @NotNull GuessResult giveUp(int currentAttempt) {
        return new Defeat(currentAttempt, maxAttempts);
    }
}
