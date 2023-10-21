package edu.project1;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Random;
import edu.project1.game.HangmanGame;
import edu.project1.game.Session;
import edu.project1.result.ErrorResult;
import edu.project1.util.DictionaryImpl;
import edu.project1.result.Defeat;
import edu.project1.result.GuessResult;
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
        assertThat(result).isNotInstanceOf(ErrorResult.class);
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
        assertThat(result).isInstanceOf(ErrorResult.class);
    }

    @Test
    @DisplayName("Give up case")
    void giveUpInput() {
        InputStream in = new ByteArrayInputStream("end".getBytes());
        System.setIn(in);
        Session sn = new Session(1);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new Defeat(1, 5));
    }

    @Test
    @DisplayName("Incorrect input when guessing")
    void notCorrectInput() {
        InputStream in = new ByteArrayInputStream("cat".getBytes());
        System.setIn(in);
        Session sn = new Session(1);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new Defeat(1, 5));
    }

    @Test
    @DisplayName("Correct guess case")
    void successInput() {
        InputStream in = new ByteArrayInputStream("я".getBytes());
        System.setIn(in);
        int levelInput = new Random().nextBoolean() ? 1 : 2;
        Session sn = new Session(levelInput);
        GuessResult result = sn.startGame();
        assertThat(result).isEqualTo(new Defeat(2, 5));
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
