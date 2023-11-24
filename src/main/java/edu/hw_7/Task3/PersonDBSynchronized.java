package edu.hw_7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class PersonDBSynchronized implements PersonDataBase {

    Map<Integer, Person> cash = new HashMap<>();

    @Override
    public synchronized void add(Person person) {
        cash.put(person.id(), person);
    }

    @Override
    public synchronized void delete(int id) {
        cash.remove(id);
    }

    @Override
    public synchronized List<Person> findByName(String name) {
        return cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList();
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        return cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList();
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        return cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList();
    }

}
