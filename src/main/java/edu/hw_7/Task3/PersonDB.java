package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@SuppressWarnings("MagicNumber")
public class PersonDB implements PersonDataBase {

    Map<Integer, Person> cash = new HashMap<>();

    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();

    @Override
    public void add(Person person) {
        cash.put(person.id(), person);
    }

    @Override
    public void delete(int id) {
        cash.remove(id);
    }

    @Override
    public List<Person> findByName(String name) {
        foundByName.clear();
        foundByName.addAll(cash.values().stream().filter(p -> Objects.equals(p.name(), name)).toList());
        return foundByName;
    }

    @Override
    public List<Person> findByAddress(String address) {
        foundByAddress.clear();
        foundByAddress.addAll(cash.values().stream().filter(p -> Objects.equals(p.address(), address)).toList());
        return foundByAddress;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        foundByPhone.clear();
        foundByPhone.addAll(cash.values().stream().filter(p -> Objects.equals(p.phoneNumber(), phone)).toList());
        return foundByPhone;
    }

}
