package edu.hw_7.Task3;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public record Person(int id, String name, String address, String phoneNumber) {
    public Person(int id, String name, String address, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }
    public Person(String name, String address, String phoneNumber) {
        this(ThreadLocalRandom.current().nextInt(1000), name, address, phoneNumber);
    }
}
