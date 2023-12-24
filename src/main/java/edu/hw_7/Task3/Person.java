package edu.hw_7.Task3;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("MagicNumber")
public record Person(int id, String name, String address, String phoneNumber) {
    public Person {
        Pattern pattern = Pattern.compile("^.([0-9]{10,12})$");
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.find() && name.isBlank() && address.isBlank() && phoneNumber.isBlank()) {
            throw new RuntimeException("some data are empty or phoneNumber is not correct: " + phoneNumber);
        }
    }

    public Person(String name, String address, String phoneNumber) {
        this(Integer.parseInt(phoneNumber.substring(4)), name, address, phoneNumber);
    }
}
