package edu.hw3.task7;

import java.util.Comparator;

public class NullComparator<T> implements Comparator<T> {
    private final Comparator<? super T> c;

    public NullComparator(Comparator<? super T> c1) {
        this.c = c1;
    }

    @Override
    public int compare(T o1, T o2) {
        if (o1 == null || o2 == null) {
            return compareNullables(o1, o2);
        } else if (c != null) {
            return c.compare(o1, o2);
        }
        return 0;
    }

    private int compareNullables(T o1, T o2) {
        if (o1 == null && o2 != null) {
            return -1;
        } else if (o1 != null && o2 == null) {
            return 1;
        } else if (o1 == null) {
            return 0;
        }
        return 0;
    }
}
