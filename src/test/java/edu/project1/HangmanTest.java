package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import edu.project1.game.HangmanGame;
import edu.project1.game.SessionManager;
import edu.project1.result.ErrorResult;
import edu.project1.result.FailedGuess;
import edu.project1.result.SuccessfulGuess;
import edu.project1.result.Win;
import edu.project1.util.DictionaryImpl;
import edu.project1.result.Defeat;
import edu.project1.result.GuessResult;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class HangmanTest {

    @Test
    @DisplayName("Correct game level")
    void testFirstInputIsCorrect() {
        int levelInput = new Random().nextBoolean() ? 1 : 2;
        String input = String.valueOf(levelInput);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        HangmanGame h = new HangmanGame();
        GuessResult result = h.startNewGame();
        assertThat(result).isNotInstanceOf(ErrorResult.class);
    }

    @Test
    @DisplayName("Not correct game level")
    void testFirstInputIsNotCorrect() {
        int levelInput = 5;
        String input = String.valueOf(levelInput);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        HangmanGame h = new HangmanGame();
        assertThatThrownBy(h::startNewGame).isInstanceOf(RuntimeException.class);
    }

    @Test
    @DisplayName("Short word")
    void gameWithNotCorrectWord() {
        Scanner sc = new Scanner(System.in);
        SessionManager sm = new SessionManager("o", 5, sc);
        sm.startGame();
        GuessResult result = sm.getResult();
        assertThat(result).isInstanceOf(ErrorResult.class);
    }

    @Test
    @DisplayName("Give up case")
    void giveUpInput() {
        Scanner sc = new Scanner("end");
        SessionManager sm = new SessionManager("no", 5, sc);
        GuessResult result = sm.checkGuess("**", 1);
        assertThat(result).isEqualTo(new Defeat(1, 5));
    }

    @Test
    @DisplayName("Incorrect input when guessing")
    void notCorrectInput() {
        Scanner sc = new Scanner("sdfsdfsdf");
        SessionManager sm = new SessionManager("no", 5, sc);
        GuessResult result = sm.checkGuess("**", 1);
        assertThat(result).isEqualTo(new ErrorResult("not valid input"));
    }

    @Test
    @DisplayName("Successful guess case")
    void successInput() {
        Scanner sc = new Scanner("o");
        SessionManager sm = new SessionManager("no", 5, sc);
        GuessResult result = sm.checkGuess("**", 1);
        assertThat(result).isEqualTo(new SuccessfulGuess(0, "*o"));
    }

    @Test
    @DisplayName("Not successful guess case")
    void failedInput() {
        Scanner sc = new Scanner("a");
        SessionManager sm = new SessionManager("no", 5, sc);
        GuessResult result = sm.checkGuess("**", 1);
        assertThat(result).isEqualTo(new FailedGuess(1, 5, "**"));
    }

    @Test
    @DisplayName("Win case")
    void winInput() {
        Scanner sc = new Scanner("a");
        SessionManager sm = new SessionManager("a", 5, sc);
        GuessResult result = sm.checkGuess("*", 1);
        assertThat(result).isEqualTo(new Win(0, 5, "a"));
    }

    @Test
    @DisplayName("Defeat case")
    void defeatInput() {
        Scanner sc = new Scanner("a");
        SessionManager sm = new SessionManager("v", 0, sc);
        GuessResult g = sm.checkGuess("*", 1);
        sm.proceedGame(g);
        GuessResult result = sm.getResult();
        assertThat(result).isEqualTo(new Defeat(1, 0));
    }

    @Test
    @DisplayName("Words' length must be more than 1")
    void dicTest() {
        DictionaryImpl d = new DictionaryImpl();
        List<String> words = d.getWords(1);
        List<String> result = words.stream().filter(it -> it.length() == 1).toList();
        var empty = 0;
        assertThat(result.size()).isEqualTo(empty);
    }

}
