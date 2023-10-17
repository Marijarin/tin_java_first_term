package edu.hw_2.task3;

public class ConnectionException extends RuntimeException {
    String message;
    Throwable cause;
    public ConnectionException(String message, Throwable cause) {
        this.message = message;
        this.cause = cause;
    }
}
