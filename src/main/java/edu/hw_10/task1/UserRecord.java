package edu.hw_10.task1;

import edu.hw_10.task1.annotations_util.MaxValue;
import edu.hw_10.task1.annotations_util.NotNull;

@SuppressWarnings("RegexpSingleLineJava")
public record UserRecord(
    @NotNull
    String name,
    @MaxValue(value = 14)
    int age
) implements User {
    @Override
    public void sayHello() {
        System.out.println("Hello from UserRecord instance");
    }
}
