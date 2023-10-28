package edu.hw4;

public class ValidationError {

    private Error error;

    public static class Error {
        final String context;
        final String errorMessage;
        final String errorValue;

        public Error(String context, String errorMessage, String errorValue) {
            this.context = context;
            this.errorMessage = errorMessage;
            this.errorValue = errorValue;
        }
    }

    public boolean isEmpty() {
        return this.error == null;
    }

    public String toString() {
        return "Error data is: " + this.error.toString();
    }

    public void setError(String context, String errorMessage, String errorValue) {
        this.error = new Error(context, errorMessage, errorValue);
    }

}
