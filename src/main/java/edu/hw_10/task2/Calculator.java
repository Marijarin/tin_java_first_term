package edu.hw_10.task2;

public interface Calculator {
    @Cache(persist = true)
    long fib(int number);

    @Cache(persist = false)
    long fi(int number);
}
