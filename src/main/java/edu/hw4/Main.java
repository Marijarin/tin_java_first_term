package edu.hw4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static edu.hw4.AnimalOperations.ANIMALS_EXAMPLE;
import static edu.hw4.AnimalOperations.fromHeavyToLight;
import static edu.hw4.AnimalOperations.fromSmallToBig;
import static edu.hw4.AnimalOperations.heaviestAnimal;
import static edu.hw4.AnimalOperations.longestName;
import static edu.hw4.AnimalOperations.speciesRange;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        LOGGER.info("Hello and welcome!");
        LOGGER.info(fromSmallToBig(ANIMALS_EXAMPLE));
        LOGGER.info(fromHeavyToLight(ANIMALS_EXAMPLE, 3));
        LOGGER.info(speciesRange(ANIMALS_EXAMPLE));
        LOGGER.info(longestName(ANIMALS_EXAMPLE));
        LOGGER.info(heaviestAnimal(ANIMALS_EXAMPLE));
    }
}
