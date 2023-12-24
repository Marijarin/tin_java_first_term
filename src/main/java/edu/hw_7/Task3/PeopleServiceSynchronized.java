package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings({"MagicNumber", "MultiLineStringLiterals", "RegexpSingleLineJava"})
public class PeopleServiceSynchronized {
    final int threads;
    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();

    public final Map<Integer, Person> inMemory = new HashMap<>();

    List<Runnable> tasks = new ArrayList<>();

    public PeopleServiceSynchronized(int threads) {
        this.threads = threads;
    }

    private synchronized void writeToMemory(Map<Integer, Person> cash) {
        inMemory.putAll(cash);
    }

    private synchronized void deleteFromMemory(int id) {
        inMemory.remove(id);
    }

    public Runnable addPerson(Person person) {
        PersonDB personDBSynchronized = new PersonDB();
        return (() -> {
            personDBSynchronized.add(person);
            writeToMemory(personDBSynchronized.cash);
        });
    }

    public Runnable deletePerson(int id) {
        PersonDB personDBSynchronized = new PersonDB();
        return (() -> {
            personDBSynchronized.delete(id);
            deleteFromMemory(id);
        });
    }

    public Runnable findByName(String name) {
        PersonDB personDBSynchronized = new PersonDB();
        return (() -> {
            synchronized (this) {
                foundByName.clear();
                personDBSynchronized.cash.putAll(inMemory);
                foundByName.addAll(personDBSynchronized.findByName(name));
            }
            if (foundByName.isEmpty()) {
                tasks.add(findByName(name));
            }
            System.out.println("\u001b[0;93mFound by name: " + name + foundByName);
        });
    }

    public Runnable findByPhone(String phone) {
        PersonDB personDBSynchronized = new PersonDB();
        return (() -> {
            synchronized (this) {
                foundByPhone.clear();
                personDBSynchronized.cash.putAll(inMemory);
                foundByPhone.addAll(personDBSynchronized.findByPhone(phone));
            }
            if (foundByPhone.isEmpty()) {
                tasks.add(findByAddress(phone));
            }
            System.out.println("\u001b[0;95mFound by phone: " + phone + foundByPhone);
        });
    }

    public Runnable findByAddress(String address) {
        PersonDB personDBSynchronized = new PersonDB();
        return (() -> {
            synchronized (this) {
                foundByAddress.clear();
                personDBSynchronized.cash.putAll(inMemory);
                foundByAddress.addAll(personDBSynchronized.findByAddress(address));
            }
            if (foundByAddress.isEmpty()) {
                tasks.add(findByAddress(address));
            }
            System.out.println(
                "\u001b[0;92mFound by address: " + address + foundByAddress);
        });
    }

    public void addTasks(Runnable... plan) {
        tasks.addAll(List.of(plan));
    }

    public void executeTasks() {
        try (ExecutorService service = Executors.newFixedThreadPool(threads)) {
            for (Runnable task : tasks) {
                service.execute(task);
            }
        } finally {
            tasks.clear();
            System.out.println("\u001b[0;96mAll people: " + inMemory);
        }
    }
}
