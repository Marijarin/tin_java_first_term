package edu.hw_7;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class FactorialCounter {
    final int howMany;

    FactorialCounter(int howMany) {
        this.howMany = howMany;
    }

    long countFactorialInParallel() {
//        var ints = new ArrayList<Integer>();
//        for (int i = 0; i < howMany; i++) {
//            ints.add(i);
//        }
//        return (long) ints.stream().parallel().reduce(1, (x, y) -> x * y);
        return IntStream.rangeClosed(2, howMany).parallel().reduce(1, (x, y) -> x * y);
    }

    long countFactorial() {
        var ints = new ArrayList<Integer>();
        for (int i = 0; i < howMany; i++) {
            ints.add(i);
        }
        return (long) ints.stream().reduce(1, (x, y) -> x * y);
    }
}
