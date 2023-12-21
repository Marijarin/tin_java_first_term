package edu.hw_7.Task3;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

@SuppressWarnings({"MagicNumber", "MultiLineStringLiterals", "RegexpSingleLineJava"})
public class PeopleServiceLock {
    final int threads;

    public final List<Person> foundByAddress = new ArrayList<>();
    public final List<Person> foundByName = new ArrayList<>();
    public final List<Person> foundByPhone = new ArrayList<>();
    public final Map<Integer, Person> inMemory = new HashMap<>();
    ReadWriteLock lock = new ReentrantReadWriteLock();

    Lock writeLock = lock.writeLock();

    Lock readLock = lock.readLock();
    List<Runnable> tasks = new ArrayList<>();

    public PeopleServiceLock(int threads) {
        this.threads = threads;
    }

    private void writeToMemory(Map<Integer, Person> cash) {
        inMemory.putAll(cash);
    }

    private void deleteFromMemory(int id) {
        inMemory.remove(id);
    }

    public Runnable addPerson(Person person) {
        PersonDB personDb = new PersonDB();
        return (() -> {
            personDb.add(person);
            try {
                writeLock.lock();
                writeToMemory(personDb.cash);
            } finally {
                writeLock.unlock();
            }
        });
    }

    public Runnable deletePerson(int id) {
        PersonDB personDb = new PersonDB();
        return (() -> {
            personDb.delete(id);
            try {
                writeLock.lock();
                deleteFromMemory(id);
            } finally {
                writeLock.unlock();
            }

        });
    }

    public Runnable findByName(String name) {
        PersonDB personDb = new PersonDB();
        return (() -> {
            try {
                readLock.lock();
                personDb.cash.putAll(inMemory);
            } finally {
                readLock.unlock();
            }
            try {
                writeLock.lock();
                foundByName.clear();
                foundByName.addAll(personDb.findByName(name));
            } finally {
                writeLock.unlock();
            }

            System.out.println("\u001b[0;93mFound by name: " + name + foundByName);
        });
    }

    public Runnable findByPhone(String phone) {
        PersonDB personDb = new PersonDB();
        return (() -> {
            try {
                readLock.lock();
                personDb.cash.putAll(inMemory);
            } finally {
                readLock.unlock();
            }
            try {
                writeLock.lock();
                foundByPhone.clear();
                foundByPhone.addAll(personDb.findByPhone(phone));
            } finally {
                writeLock.unlock();
            }
            System.out.println("\u001b[0;95mFound by phone: " + phone + foundByPhone);
        });
    }

    public Runnable findByAddress(String address) {
        PersonDB personDb = new PersonDB();
        return (() -> {
            try {
                readLock.lock();
                personDb.cash.putAll(inMemory);
            } finally {
                readLock.unlock();
            }
            try {
                writeLock.lock();
                foundByAddress.clear();
                foundByAddress.addAll(personDb.findByAddress(address));
            } finally {
                writeLock.unlock();
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

