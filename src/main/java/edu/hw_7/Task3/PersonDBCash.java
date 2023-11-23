package edu.hw_7.Task3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PersonDBCash implements PersonDataBase{

    Map<Integer, Person> cash = new HashMap<>();
    @Override
    public void add(Person person) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Person> findByName(String name) {
        return null;
    }

    @Override
    public List<Person> findByAddress(String address) {
        return null;
    }

    @Override
    public List<Person> findByPhone(String phone) {
        return null;
    }
}
