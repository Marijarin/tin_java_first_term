package edu.hw3;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        LOGGER.info("Hello and welcome!");

        String source = "Hello world!";
        Task1 t1 = new Task1();
        LOGGER.info(t1.atbash(source));
        Task2 t2 = new Task2();
        LOGGER.info(t2.clusterize("((())())(()"));
        Task3 t3 = new Task3();
        LOGGER.info(t3.freqDict(new Integer[] {1, 1, 2, 2}));
        Task4 t4 = new Task4();
        LOGGER.info(t4.convertToRoman(99));

    }
}
