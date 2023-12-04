package edu.project1.game;

@SuppressWarnings({"MagicNumber", "RegexpSingleLineJava", "MultipleStringLiterals"})
public class Renderer {
    public void renderHangman(int remainingAttempts) {
        StringBuilder sb = new StringBuilder();
        switch (remainingAttempts) {
            case 4 -> fourAttempts(sb);
            case 3 -> threeAttempts(sb);
            case 2 -> twoAttempts(sb);
            case 1 -> oneAttempt(sb);
            case 0 -> noAttempts(sb);
            default -> fiveAttempts(sb);
        }
        System.out.print(sb);
    }

    private void upperLevel(StringBuilder sb) {
        // Level 7
        sb.append("  \u001b[0;93m┏━━━┓\u001b[0m \n");
        // Level 6
        sb.append("  \u001b[0;93m┃\u001b[0m   ");
    }

    private void lowerLevel(StringBuilder sb) {
        sb.append("  \u001b[0;93m┃\u001b[0m     \n");
        // Level 2
        sb.append("  \u001b[0;93m┃\u001b[0m     \n");
        // Level 1
        sb.append("  \u001b[0;93m┃\u001b[0m     \n");
        // Level 0
        sb.append("\u001b[0;93m━━┻━━━━━\u001b[0m\n");
    }

    private void middleLevels(StringBuilder sb) {
        sb.append("\n");
        sb.append("  \u001b[0;93m┃\u001b[0m  ");
    }

    private void fiveAttempts(StringBuilder sb) {
        upperLevel(sb);
        sb.append(" ");
        middleLevels(sb);
        sb.append(" ");
        middleLevels(sb);
        sb.append("   ");
        sb.append("\n");
        lowerLevel(sb);
    }

    private void fourAttempts(StringBuilder sb) {
        upperLevel(sb);
        sb.append("\u001b[0;92m◓\u001b[0m");
        middleLevels(sb);
        sb.append(" ");
        middleLevels(sb);
        sb.append("   ");
        sb.append("\n");
        lowerLevel(sb);
    }

    private void threeAttempts(StringBuilder sb) {
        upperLevel(sb);
        sb.append("\u001b[0;92m◓\u001b[0m");
        middleLevels(sb);
        sb.append(" \u001b[0;92m│╮\u001b[0m");
        middleLevels(sb);
        sb.append("   ");
        sb.append("\n");
        lowerLevel(sb);
    }

    private void twoAttempts(StringBuilder sb) {
        upperLevel(sb);
        sb.append("\u001b[0;92m◓\u001b[0m");
        middleLevels(sb);
        sb.append("\u001b[0;92m╭│╮\u001b[0m");
        middleLevels(sb);
        sb.append("   ");
        sb.append("\n");
        lowerLevel(sb);
    }

    private void oneAttempt(StringBuilder sb) {
        upperLevel(sb);
        sb.append("\u001b[0;92m◓\u001b[0m");
        middleLevels(sb);
        sb.append("\u001b[0;92m╭│╮\u001b[0m");
        middleLevels(sb);
        sb.append(" \u001b[0;92m└╮\u001b[0m");
        sb.append("\n");
        lowerLevel(sb);
    }

    private void noAttempts(StringBuilder sb) {
        upperLevel(sb);
        sb.append("\u001b[0;92m◓\u001b[0m");
        middleLevels(sb);
        sb.append("\u001b[0;92m╭│╮\u001b[0m");
        middleLevels(sb);
        sb.append("\u001b[0;92m╭┴╮\u001b[0m");
        sb.append("\n");
        lowerLevel(sb);
    }

    public void letterPrompt() {
        System.out.print("\u001b[0;1;95mYour guess ⇛ \u001b[0;1;95m");
    }

    public void reset() {
        System.out.print("\u001b[0m");
    }

    public void win(String message) {
        System.out.print("\u001b[0;1;92m✓ ");
        System.out.println(message);
        reset();
    }

    public void lose(String message) {
        System.out.print("\u001b[0;1;91m✗ ");
        System.out.println(message);
        reset();
    }
}
