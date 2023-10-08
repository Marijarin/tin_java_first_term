package edu.hw1;

import java.util.Arrays;
import java.util.Scanner;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        // LOGGER.info("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        //for (int i = 0; i <= 2; i++) {

        // Press Shift+F9 to start debugging your code. We have set one breakpoint
        // for you, but you can always add more by pressing Ctrl+F8.
        //LOGGER.info("i = {}", i);
        //}
        Task0 task0 = new Task0();
        Task1 task1 = new Task1();
        Task2 task2 = new Task2();
        Task3 task3 = new Task3();
        Task4 task4 = new Task4();
        Task5 task5 = new Task5();
        Task6 task6 = new Task6();
        Task7 task7 = new Task7();
        Task8 task8 = new Task8();

        // Run Task0
        String hello = "Привет, мир!";
        task0.helloWorld(hello, LOGGER);

        //Run Task1
        String vLength7 = " 01 : 11 ";
        LOGGER.info(task1.minutesToSeconds(vLength7));

        //Run Task2
        long num = -1000000000L;
        LOGGER.info(task2.countDigits(num));

        //Run Task3
        Scanner sc = new Scanner(System.in);
        int[] into;
        int[] outer;
        try {
            into = Arrays.stream(sc.nextLine().split(".*")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            outer = Arrays.stream(sc.nextLine().split(".*")).map(String::trim).mapToInt(Integer::parseInt).toArray();
            LOGGER.info(task3.isNestable(into, outer));
        } catch (Exception e) {
            LOGGER.info("Something went wrong: " + e);
        }

        //Run Task4
        String broken = sc.nextLine();
        LOGGER.info(task4.fixString(broken));

        //Run Task5
        long parent = sc.nextLong();
        LOGGER.info(task5.isPalindromeDescendant(parent));

        //Run Task6
        int goToK = sc.nextInt();
        LOGGER.info(task6.countKSteps(goToK));
        sc.close();

        //Run Task7
        LOGGER.info(task7.rotateRight(8, 1));
        LOGGER.info(task7.rotateLeft(16, 1));
        LOGGER.info(task7.rotateLeft(17, 2));
        //Run Task8
        int[][] board = {
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 1, 0, 0, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 0},
            {1, 0, 0, 0, 0, 0, 0, 0}
        };
        LOGGER.info(task8.knightBoardCapture(board));
    }
}
