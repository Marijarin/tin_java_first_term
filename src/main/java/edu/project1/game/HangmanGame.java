package edu.project1.game;


import edu.project1.result.ErrorResult;
import edu.project1.result.GuessResult;
import edu.project1.util.Printable;
import java.util.Scanner;

public class HangmanGame implements Printable {
    public GuessResult startNewSession() {
        Scanner sc = new Scanner(System.in);
        LOGGER.info("\nSelect the level -> print a number 1 or 2");
        int level = sc.nextInt();
        if (level == 1 || level == 2) {
            Session s = new Session(level);
            return s.startGame();
        } else {
            LOGGER.info("\nWrong input -> start the game again");
            return new ErrorResult("Level must be 1 or 2");
        }
    }
}
