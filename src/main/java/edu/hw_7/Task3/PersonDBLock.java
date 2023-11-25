package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PersonDBLock implements PersonDataBase {
    Map<Integer, Person> cash = new HashMap<>();

    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();

    ReadWriteLock lock = new ReentrantReadWriteLock();

    Lock writeLock = lock.writeLock();

    Lock readLock = lock.readLock();

    @Override
    public void add(Person person) {
        try {
            writeLock.lock();
            cash.put(person.id(), person);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public void delete(int id) {
        try {
            writeLock.lock();
            cash.remove(id);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public List<Person> findByName(String name) {
        try {
            readLock.lock();
            foundByName.addAll(cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList());
            return foundByName;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByAddress(String address) {
        try {
            readLock.lock();
            foundByAddress.addAll(cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList());
            return foundByAddress;
        } finally {
            readLock.unlock();
        }
    }

    @Override
    public List<Person> findByPhone(String phone) {
        try {
            readLock.lock();
            foundByPhone.addAll(cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList());
            return foundByPhone;
        } finally {
            readLock.unlock();
        }
    }

}
