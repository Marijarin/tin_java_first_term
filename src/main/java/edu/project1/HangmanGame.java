package edu.project1;

public class HangmanGame {
    public GuessResult startNewSession() {
        Session s = new Session();
        return s.startGame();
    }
}
