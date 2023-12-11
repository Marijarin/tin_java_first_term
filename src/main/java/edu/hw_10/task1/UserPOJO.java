package edu.hw_10.task1;

public class UserPOJO implements User{
    private String name;
    private int age;
    public UserPOJO(){}

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
    public String toString(){
        return this.name + "-" + this.age;
    }

    public User create(String name, int age) {
        return new UserPOJO(name, age);
    }
}

