package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("MagicNumber")
public class PersonDBSynchronized implements PersonDataBase {

    Map<Integer, Person> cash = new HashMap<>();

    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();

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
        foundByName.clear();
        foundByName.addAll(cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList());
        if (foundByName.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            foundByName.addAll(cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList());
            return foundByName;
        }
        return foundByName;
    }

    @Override
    public synchronized List<Person> findByAddress(String address) {
        foundByAddress.clear();
        foundByAddress.addAll(cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList());
        if (foundByAddress.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            foundByAddress.addAll(cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList());
            return foundByAddress;
        }
        return foundByAddress;
    }

    @Override
    public synchronized List<Person> findByPhone(String phone) {
        foundByPhone.clear();
        foundByPhone.addAll(cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList());
        if (foundByPhone.isEmpty()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            foundByPhone.addAll(cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList());
            return foundByPhone;
        }
        return foundByPhone;
    }

}
