package edu.hw_2.task3;

public class ConnectionException extends RuntimeException {
    final String message;
    final Throwable cause;

    public ConnectionException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
