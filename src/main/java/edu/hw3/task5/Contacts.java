package edu.hw3.task5;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Contacts {
    private final List<Person> contacts;

    public Contacts() {
        this.contacts = new ArrayList<>();
    }

    public void addContact(Person p) {
        contacts.add(p);
    }

    public List<Person> getContacts() {
        return contacts;
    }

    public void addContacts(List<Person> cs) {
        contacts.addAll(cs);
    }

    public List<Person> parseContacts(List<Person> cs, SortingType sortingType) {
        List<Person> cts;
        cts = Objects.requireNonNullElse(cs, this.contacts);
        List<Person> sortedDsc = cts.stream().sorted().toList();
        switch (sortingType) {
            case ASC -> {
                return sortedDsc;
            }
            case DESC -> {
                return sortedDsc.stream().toList().reversed();
            }
            default -> {
                return contacts;
            }
        }
    }
}
