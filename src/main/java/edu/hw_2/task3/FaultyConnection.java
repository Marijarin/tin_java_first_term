package edu.hw_2.task3;

import java.util.logging.Logger;

public class FaultyConnection implements Connection {
    Throwable throwable = new RuntimeException("Faulty connection failed");
    Logger logger = Logger.getLogger("Faulty connection");

    @Override
    public void execute(String command) {
        if (command.contains("a")) {
            logger.info("Executing: " + command);
        } else {
            String message = "Your command:" + command + " cannot be executed";
            throw new ConnectionException(message, throwable);
        }
    }

    @Override
    public void close() throws Exception {
        logger.info("Faulty connection is closed");
        this.logger = null;
    }
}
