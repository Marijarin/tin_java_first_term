package edu.project1.game;

import edu.project1.result.ErrorResult;
import edu.project1.result.GuessResult;
import edu.project1.util.Printable;

public class HangmanGame implements Printable {
    public GuessResult startNewGame() {
        Session s = new Session();
        GuessResult g;
        try {
            s.initSession();
            g = s.takeResult();
        } catch (RuntimeException e) {
            g = new ErrorResult(e.getLocalizedMessage());
        }
        return g;
    }
}

