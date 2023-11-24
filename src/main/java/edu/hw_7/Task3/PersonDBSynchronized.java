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
        var found = cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList();
        if (found.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList();
        }
        return found;
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        var found = cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList();
        if (found.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList();
        }
        return found;
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        var found =  cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList();
        if (found.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            return cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList();
        }
       return found;
    }

}
