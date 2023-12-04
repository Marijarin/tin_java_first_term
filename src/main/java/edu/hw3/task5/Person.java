package edu.hw3.task5;

import org.jetbrains.annotations.NotNull;

public record Person(String name, String surname) implements Comparable<Person> {
    public Person(String name) {
        this(name, "");
    }

    public String getDataToCompare() {
        if (this.surname.isEmpty() || this.surname.isBlank()) {
            return this.name;
        }
        return this.surname;
    }

    @Override
    public int compareTo(@NotNull Person o) {
        return CharSequence.compare(this.getDataToCompare(), o.getDataToCompare());
    }
}
