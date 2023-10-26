package edu.hw3.task8;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class BackwardIterator<E> implements Iterator<E> {
    private int currentIndex;
    private final List<E> list;

    public BackwardIterator(List<E> list) {
        this.currentIndex = list.size() - 1;
        this.list = list;
    }

    @Override
    public boolean hasNext() {
        return currentIndex >= 0;
    }

    @Override
    public E next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return list.get(currentIndex--);
    }
}
