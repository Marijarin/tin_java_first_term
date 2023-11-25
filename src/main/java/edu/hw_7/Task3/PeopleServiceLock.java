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
    public final PersonDBLock personDBLock = new PersonDBLock();

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
        return (() -> {
            personDBLock.add(person);
            writeToMemory(personDBLock.cash);
        });
    }

    public Runnable deletePerson(int id) {
        return (() -> {
            personDBLock.delete(id);
            deleteFromMemory(id);
        });
    }

    public Runnable findByName(String name) {
        return (() -> {
            System.out.println("\u001b[0;93mFound by name: " + name + personDBLock.findByName(name));
        });
    }

    public Runnable findByPhone(String phone) {
        return (() -> {
            System.out.println("\u001b[0;95mFound by phone: " + phone + personDBLock.findByPhone(phone));
        });
    }

    public Runnable findByAddress(String address) {
        return (() -> {
            System.out.println(
                "\u001b[0;92mFound by address: " + address + personDBLock.findByAddress(address));
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

