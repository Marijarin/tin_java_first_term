package edu.hw_10.task1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

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
        LOGGER.info(randomObjectGenerator.nextObject(UserRecord.class));
        LOGGER.info(randomObjectGenerator.nextObject(UserPOJO.class));
        LOGGER.info(randomObjectGenerator.nextObject(UserPOJO.class, "create"));
    }
}
