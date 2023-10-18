package edu.hw_2.task3;

import java.util.logging.Logger;

public class FaultyConnection implements Connection {
    Throwable throwable = new RuntimeException("Faulty connection failed");
    Logger logger = Logger.getLogger("Faulty connection");

    @Override
    public void execute(String command) {
        if (command.contains("&&")) {
            logger.info("Executing: " + command);
        } else {
            String message = "Your command:" + command + " cannot be executed";
            throw new ConnectionException(message, throwable);
        }
    }

    @Override
    public void close() {
        try {
            logger.info("Faulty connection is closed");
            this.logger = null;
        } catch (Exception e) {
            Logger.getLogger(String.valueOf(e.getCause()));
        }
    }
}
