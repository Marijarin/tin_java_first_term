package edu.project1.game;

import edu.project1.result.GuessResult;
import edu.project1.util.Dictionary;
import edu.project1.util.DictionaryImpl;
import edu.project1.util.Printable;
import java.util.Scanner;
import org.jetbrains.annotations.NotNull;

public class Session implements Printable {
    private final Scanner sc;
    private final SessionManager sm;
    private final int maxAttempts = 5;

    public Session() {
        sc = new Scanner(System.in);
        int level = chooseLevel();
        Dictionary dictionary = new DictionaryImpl();
        String puzzle = dictionary.randomWord(level);
        sm = new SessionManager(puzzle, maxAttempts, sc);
    }

    public int chooseLevel() {
        LOGGER.info("\nSelect the level -> print y number 1 or 2");
        int level = 0;
        try {
            level = Integer.parseInt(sc.nextLine().trim());
        } catch (NumberFormatException e) {
            LOGGER.info(e);
        }
        if (level == 1 || level == 2) {
            return level;
        } else {
            throw new RuntimeException("*** not valid level *** it must be 1 or 2 ***");
        }
    }

    public void initSession() {
        sm.startGame();
    }

    @NotNull public GuessResult takeResult() {
        return sm.getResult();
    }
}
