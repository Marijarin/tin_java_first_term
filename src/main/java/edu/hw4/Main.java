package edu.hw4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.List;
import static edu.hw4.AnimalOperations.ANIMALS_EXAMPLE;
import static edu.hw4.AnimalOperations.ANIMALS_EXAMPLE_SECOND;
import static edu.hw4.AnimalOperations.ageNumberNotPawsNumber;
import static edu.hw4.AnimalOperations.allPawsNumber;
import static edu.hw4.AnimalOperations.canByteAndMoreThan100;
import static edu.hw4.AnimalOperations.fromHeavyToLight;
import static edu.hw4.AnimalOperations.fromSmallToBig;
import static edu.hw4.AnimalOperations.heaviestAnimal;
import static edu.hw4.AnimalOperations.heaviestFishEver;
import static edu.hw4.AnimalOperations.isDogMoreKHeight;
import static edu.hw4.AnimalOperations.longestName;
import static edu.hw4.AnimalOperations.mostFrequent;
import static edu.hw4.AnimalOperations.mostHeaviestKAnimalLowerK;
import static edu.hw4.AnimalOperations.mostOldKAnimal;
import static edu.hw4.AnimalOperations.namesInMoreTwoWords;
import static edu.hw4.AnimalOperations.numberAnimalsWeightMoreHeight;
import static edu.hw4.AnimalOperations.speciesRange;
import static edu.hw4.AnimalOperations.spidersBitesMoreDogs;
import static edu.hw4.AnimalOperations.sumWeightFromKToLAge;
import static edu.hw4.AnimalOperations.tripleSorting;

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
        LOGGER.info(mostOldKAnimal(ANIMALS_EXAMPLE, 3));
        LOGGER.info(mostHeaviestKAnimalLowerK(ANIMALS_EXAMPLE, 20));
        LOGGER.info(allPawsNumber(ANIMALS_EXAMPLE));
        LOGGER.info(ageNumberNotPawsNumber(ANIMALS_EXAMPLE));
        LOGGER.info(mostFrequent(ANIMALS_EXAMPLE));
        LOGGER.info(canByteAndMoreThan100(ANIMALS_EXAMPLE));
        LOGGER.info(numberAnimalsWeightMoreHeight(ANIMALS_EXAMPLE));
        LOGGER.info(namesInMoreTwoWords(ANIMALS_EXAMPLE));
        LOGGER.info(isDogMoreKHeight(ANIMALS_EXAMPLE, 10));
        LOGGER.info(sumWeightFromKToLAge(ANIMALS_EXAMPLE, 0, 2));
        LOGGER.info(tripleSorting(ANIMALS_EXAMPLE));
        LOGGER.info(spidersBitesMoreDogs(ANIMALS_EXAMPLE));
        LOGGER.info(heaviestFishEver(List.of(ANIMALS_EXAMPLE, ANIMALS_EXAMPLE_SECOND)));
    }
}
