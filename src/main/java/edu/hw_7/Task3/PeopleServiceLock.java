package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SuppressWarnings({"MagicNumber", "MultiLineStringLiterals", "RegexpSingleLineJava"})
public class PeopleServiceLock {
    final int threads;

    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();
    public final Map<Integer, Person> inMemory = new HashMap<>();

    List<Runnable> tasks = new ArrayList<>();

    public PeopleServiceLock(int threads) {
        this.threads = threads;
    }

    private synchronized void writeToMemory(Map<Integer, Person> cash) {
        inMemory.putAll(cash);
    }

    private synchronized void deleteFromMemory(int id) {
        inMemory.remove(id);
    }

    public Runnable addPerson(Person person) {
        PersonDBLock personDBLock = new PersonDBLock();
        return (() -> {
            personDBLock.add(person);
            writeToMemory(personDBLock.cash);
        });
    }

    public Runnable deletePerson(int id) {
        PersonDBLock personDBLock = new PersonDBLock();
        return (() -> {
            personDBLock.delete(id);
            deleteFromMemory(id);
        });
    }

    public Runnable findByName(String name) {
        PersonDBLock personDBLock = new PersonDBLock();
        return (() -> {
            foundByName.clear();
            personDBLock.cash.putAll(inMemory);
            foundByName.addAll(personDBLock.findByName(name));
            System.out.println("\u001b[0;93mFound by name: " + name + foundByName);
        });
    }

    public Runnable findByPhone(String phone) {
        PersonDBLock personDBLock = new PersonDBLock();
        return (() -> {
            foundByPhone.clear();
            personDBLock.cash.putAll(inMemory);
            foundByPhone.addAll(personDBLock.findByPhone(phone));
            System.out.println("\u001b[0;95mFound by phone: " + phone + foundByPhone);
        });
    }

    public Runnable findByAddress(String address) {
        PersonDBLock personDBLock = new PersonDBLock();
        return (() -> {
            foundByAddress.clear();
            personDBLock.cash.putAll(inMemory);
            foundByAddress.addAll(personDBLock.findByAddress(address));
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

