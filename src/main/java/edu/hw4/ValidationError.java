package edu.hw4;

import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ValidationError {

    private Error error;

    ValidationError() {
    }

    ;

    private ValidationError(Error error) {
        this.error = error;
    }

    public boolean isEmpty() {
        return this.error == null;
    }

    public String toString() {
        if (this.error != null) {
            return this.error.errorValue + ":" + this.error.errorMessage;
        }
        return null;
    }

    public ValidationError checkNegMeasurement(int number) {
        if (number <= 0) {
            return new ValidationError(new Error("parameter must be > 0", String.valueOf(number)));
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
        List<String> names = List.of(name.split(" "));
        if (names.isEmpty()) {
            return new ValidationError(new Error("empty name", name));
        }
        Pattern letterCap = Pattern.compile("[A-Z]");
        for (String item : names) {
            Matcher hasLetterCap0 = letterCap.matcher(item.substring(0, 1));
            Matcher hasLetterCapOthers = letterCap.matcher(item.substring(1));
            if (!hasLetterCap0.find() && hasLetterCapOthers.find()) {
                return new ValidationError(new Error("parameter must match first name pattern", name));
            }
        }
        return new ValidationError(null);
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
                if (animal.weight() > 5 || animal.height() > 30) {
                    return tooBig;
                }
            }
            default -> {
                return new ValidationError(null);
            }
        }
        return new ValidationError(null);
    }

    public Set<ValidationError> checkAnimal(Animal animal) {
        var namePatternCheck = checkFirstNamePattern(animal.name());
        var specialCharacters = checkNumbersAndSpecialsInName(animal.name());
        var negAge = checkNegMeasurement(animal.age());
        var negWeight = checkNegMeasurement(animal.weight());
        var negHeight = checkNegMeasurement(animal.height());
        var tooBig = checkTooBigAnimals(animal);
        var errorSet = new java.util.HashSet<>(Set.of(
            negAge,
            negHeight,
            namePatternCheck,
            specialCharacters,
            negWeight,
            tooBig
        ));
        return errorSet.stream().filter(validationError -> !validationError.isEmpty()).collect(Collectors.toSet());
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
