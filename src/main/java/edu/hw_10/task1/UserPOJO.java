package edu.hw_10.task1;

import edu.hw_10.task1.annotations_util.MinValue;
import edu.hw_10.task1.annotations_util.NotNull;

public class UserPOJO implements User {
    @NotNull
    private String name;
    @MinValue(value = 50)
    private int age;

    public UserPOJO() {
    }

    public UserPOJO(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public void sayHello() {
        System.out.println("Hello from UserPOJO instance");
    }

    @Override
    public String toString() {
        return this.name + "-" + this.age;
    }

    public User create(String name, int age) {
        return new UserPOJO(name, age);
    }
}

