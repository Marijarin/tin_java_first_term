package edu.hw_2.task3;

import java.util.logging.Logger;

public class StableConnection implements Connection {
    Logger logger = Logger.getLogger("Stable connection");

    @Override
    public void execute(String command) {
        logger.info("Executing: " + command);
    }

    @Override
    public void close() {
        try {
            logger.info("Stable connection is closed");
            this.logger = null;
        } catch (Exception e) {
            Logger.getLogger(String.valueOf(e.getCause()));
        }
    }
}
