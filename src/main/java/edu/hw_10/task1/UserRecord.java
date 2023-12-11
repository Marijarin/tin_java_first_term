package edu.hw_10.task1;

public record UserRecord(String name, int age) implements User {
    @Override
    public void sayHello() {
        System.out.println("Hello from UserRecord instance");
    }
}
