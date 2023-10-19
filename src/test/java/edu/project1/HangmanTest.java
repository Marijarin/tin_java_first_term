package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Random;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {

    @Test
    @DisplayName("Input level test 1")
    void testFirstInputIsCorrect() {
        int levelInput = new Random().nextBoolean() ? 1 : 2;
        String input = String.valueOf(levelInput);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        HangmanGame h = new HangmanGame();
        GuessResult result = h.startNewSession();
        assertThat(result).isNotInstanceOf(GuessResult.Error.class);
    }

    @Test
    @DisplayName("Input level test 2")
    void testFirstInputIsNotCorrect() {
        int levelInput = 5;
        String input = String.valueOf(levelInput);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        HangmanGame h = new HangmanGame();
        GuessResult result = h.startNewSession();
        assertThat(result).isInstanceOf(GuessResult.Error.class);
    }

    @Test
    @DisplayName("Give up case")
    void giveUpInput() {
        InputStream in = new ByteArrayInputStream("end".getBytes());
        System.setIn(in);
        Session sn = new Session(1);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new GuessResult.Defeat(1, 5));
    }

    @Test
    @DisplayName("Incorrect input when guessing")
    void notCorrectInput() {
        InputStream in = new ByteArrayInputStream("cat".getBytes());
        System.setIn(in);
        Session sn = new Session(1);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new GuessResult.Defeat(1, 5));
    }

    @Test
    @DisplayName("Correct guess case")
    void successInput() {
        InputStream in = new ByteArrayInputStream("я".getBytes());
        System.setIn(in);
        int levelInput = new Random().nextBoolean() ? 1 : 2;
        Session sn = new Session(levelInput);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new GuessResult.Defeat(2, 5));
    }
    @Test
    @DisplayName("Session test")
    void sessionTest(){
        Session sn = new Session(1);
        String result = sn.getPuzzle();
        assertThat(result.length()).isGreaterThan(1);
    }
}
