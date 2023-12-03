package edu.hw_5;

import edu.hw_5.task1_task2.DateUtil;
import java.time.LocalDate;
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

        DateUtil du = new DateUtil();
        LOGGER.info(du.showAverageTime(du.intervals));
        LOGGER.info(du.fridays13(2013));
        LOGGER.info(du.nextFriday13(LocalDate.of(2012, 8, 13)));
    }
}
