package edu.hw_2.task3;

public class ConnectionException extends RuntimeException {
    final String message;

    public ConnectionException(String message, Throwable cause) {
        this.message = message;
        this.initCause(cause);
    }
}
