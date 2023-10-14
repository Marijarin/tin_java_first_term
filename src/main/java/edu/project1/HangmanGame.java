package edu.project1;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HangmanGame {
    Dictionary dictionary = new Dictionary();
    private String puzzle = "";

    private static class Dictionary {
        private final List<String> words = Arrays.asList(
            "scratch",
            "fishhook",
            "zipper",
            "kilobyte",
            "jelly",
            "ivy",
            "I",
            "no",
            "",
            " ",
            "peekaboo"
        );

        private String getRandomWord() {
            int random = (int) (Math.random() * words.size());
            return words.get(random);
        }
    }

    private void proceedGame( String response) {
        Scanner sc = new Scanner(System.in);
        int x = 1;
        StringBuilder responseN = new StringBuilder();
        responseN.append(response);
        while (x <= 5 && !responseN.toString().equals(puzzle)) {
            System.out.println("Guess a letter:");
            String guess = sc.nextLine().trim();
            if (guess.equals("end")) {
                System.out.println("Game over");
                sc.close();
                break;
            }
            if (puzzle.contains(guess)) {
                System.out.println("Hit");
                for (int i = 0; i < puzzle.length(); i++) {
                    if (puzzle.charAt(i) == guess.toCharArray()[0]) {
                        responseN.replace(i, i + 1, guess);
                    }
                }
                System.out.println("The word is: " + responseN);
                if (responseN.toString().equals(puzzle)) {
                    System.out.println("You won!");
                    sc.close();
                    break;
                }
            } else {
                System.out.println("Missed, mistake " + x + " out of 5.");
                x++;
            }
        }
        if (x > 5) {
            System.out.println("You lost!");
            sc.close();
        }
    }

    void startGame() {
        puzzle = dictionary.getRandomWord();
        if (puzzle.isEmpty() || puzzle.length() < 2) {
            System.out.println("Oh no, the word is bad. It is: " + puzzle + " Please, rerun main");
        } else {
            System.out.println(
                "You can make only 5 mistakes.\nIf you want to finish the game, please, print end in separate line");
            String response = "*".repeat(puzzle.length());
            proceedGame(response);
        }
    }
}
