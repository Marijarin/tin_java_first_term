package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PeopleServiceSynchronized {
    final int threads;
    PersonDBSynchronized personDBSynchronized = new PersonDBSynchronized();

    Map<Integer, Person> inMemory = new HashMap<>();

    List<Runnable> tasks = new ArrayList<>();

    public PeopleServiceSynchronized(int threads) {
        this.threads = threads;
    }

    private synchronized void writeToMemory(Map<Integer, Person> cash) {
        inMemory.putAll(cash);
    }

    public Runnable addPerson(Person person) {
        return (() -> {
            personDBSynchronized.add(person);
            writeToMemory(personDBSynchronized.cash);
        });
    }

    public Runnable deletePerson(int id) {
        return (() -> {
            personDBSynchronized.delete(id);
            writeToMemory(personDBSynchronized.cash);
        });
    }

    public Runnable findByName(String name) {
        return (() -> {
            System.out.println("\u001b[0;93mFound by name: " + name + personDBSynchronized.findByName(name));
        });
    }

    public Runnable findByPhone(String phone) {
        return (() -> {
            System.out.println("\u001b[0;95mFound by phone: " + phone + personDBSynchronized.findByPhone(phone));
        });
    }

    public Runnable findByAddress(String address) {
        return (() -> {
            System.out.println(
                "\u001b[0;92mFound by address: " + address + personDBSynchronized.findByAddress(address));
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
