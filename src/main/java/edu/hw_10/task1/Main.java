package edu.hw_10.task1;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args)
        throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        RandomObjectGenerator randomObjectGenerator = new RandomObjectGenerator();
        var user1 = randomObjectGenerator.nextObject(UserRecord.class);
        LOGGER.info(user1);
        var user2 = randomObjectGenerator.nextObject(UserPOJO.class);
        var user3 = randomObjectGenerator.nextObject(UserPOJO.class, "create");
        LOGGER.info(user2);
        LOGGER.info(user3);
    }

}

