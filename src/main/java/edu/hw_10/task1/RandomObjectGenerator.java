package edu.hw_10.task1;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomObjectGenerator {
    String[] randomNames = {"Andrew", "Masha", "Slava", "Ann", "Jane"};
    int[] ages = {3, 41, 5, 16, 78};

    public Object nextObject(Class<?> objectClass, String methodName)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String name = randomNames[ThreadLocalRandom.current().nextInt(0, 5)];
        int age = ages[ThreadLocalRandom.current().nextInt(0, 5)];
        return objectClass
            .getMethod(methodName, String.class, int.class)
            .invoke(objectClass.getConstructor().newInstance(), name, age);
    }

    public Object nextObject(Class<?> objectClass)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String name = randomNames[ThreadLocalRandom.current().nextInt(0, 5)];
        int age = ages[ThreadLocalRandom.current().nextInt(0, 5)];
        return objectClass
            .getConstructor(String.class, int.class)
            .newInstance(name, age);
    }

}
