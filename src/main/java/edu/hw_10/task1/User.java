package edu.hw_10.task1;

public interface User {
    default void sayHello() {
        System.out.println("Hello");
    }

}
