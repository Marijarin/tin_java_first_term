package edu.hw_2.task3;

import java.util.logging.LogManager;
import java.util.logging.Logger;

public class StableConnection implements Connection {
    Logger logger = Logger.getLogger("Stable connection");

    @Override
    public void execute(String command) {
        logger.info("Executing: " + command);
    }

    @Override
    public void close() throws Exception {
        logger.info("Stable connection is closed");
        this.logger = null;
    }
}
