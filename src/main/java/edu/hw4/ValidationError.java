package edu.hw4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationError {

    private final Error error;

    private ValidationError(Error error) {
        this.error = error;
    }

    public boolean isEmpty() {
        return this.error == null;
    }

    public String toString() {
        return "Error data is: " + this.error.toString();
    }

    public ValidationError checkNegMeasurement(Number number) {
        if (number.longValue() <= 0 || number.doubleValue() <= 0) {
            return new ValidationError(new Error("parameter must be > 0", number.toString()));
        }
        return new ValidationError(null);
    }

    public ValidationError checkNumbersAndSpecialsInName(String name) {
        Pattern letter = Pattern.compile("[a-zA-Z]");
        Pattern digit = Pattern.compile("[0-9]");
        Pattern special = Pattern.compile("[!@#$%&*()_+=|<>?{}\\[\\]~-]");
        Matcher hasLetter = letter.matcher(name);
        Matcher hasDigit = digit.matcher(name);
        Matcher hasSpecial = special.matcher(name);
        if (!hasDigit.find() && !hasSpecial.find() && hasLetter.find()) {
            return new ValidationError(null);
        }
        return new ValidationError(new Error("parameter must contain only letters", name));
    }

    @SuppressWarnings("MagicNumber")
    public ValidationError checkFirstNamePattern(String name) {
        Pattern letterCap = Pattern.compile("[A-Z]");
        Matcher hasLetterCap0 = letterCap.matcher(name.substring(0, 1));
        Matcher hasLetterCapOthers = letterCap.matcher(name.substring(1));
        if (hasLetterCap0.find() && !hasLetterCapOthers.find()) {
            return new ValidationError(null);
        }
        return new ValidationError(new Error("parameter must match first name pattern", name));
    }

    @SuppressWarnings("MagicNumber")
    public ValidationError checkTooBigAnimals(Animal animal) {
        ValidationError tooBig = new ValidationError(new Error("animal can not be so big", animal.name()));
        switch (animal.type()) {
            case CAT, DOG, BIRD -> {
                if (animal.weight() > 100 || animal.height() > 120) {
                    return tooBig;
                }
            }
            case SPIDER -> {
                if (animal.weight() > 5 || animal.height() > 40) {
                    return tooBig;
                }
            }
            default -> {
                return new ValidationError(null);
            }
        }
        return new ValidationError(null);
    }

    public static class Error {
        final String errorMessage;
        final String errorValue;

        public Error(String errorMessage, String errorValue) {
            this.errorMessage = errorMessage;
            this.errorValue = errorValue;
        }
    }
}
