package edu.hw_10.task1;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.ValidatorFactory;
import java.lang.reflect.InvocationTargetException;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@SuppressWarnings("MagicNumber")
public class RandomObjectGenerator {
    String[] randomNames = {null, "Andrew", "Masha", "Slava", null, "Ann", "Jane"};
    int[] randomAges = {3, 51, 5, 16, 78, -11, 0};

    public Object nextObject(Class<?> objectClass, String methodName)
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        String name = randomNames[ThreadLocalRandom.current().nextInt(0, 7)];
        int age = randomAges[ThreadLocalRandom.current().nextInt(0, 7)];
        var obj = objectClass
            .getMethod(methodName, String.class, int.class)
            .invoke(objectClass.getConstructor().newInstance(), name, age);
        if (isValid(obj)) {
            return obj;
        }
        return nextObject(obj.getClass());
    }

    public Object nextObject(Class<?> objectClass)
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String name = randomNames[ThreadLocalRandom.current().nextInt(0, 5)];
        int age = randomAges[ThreadLocalRandom.current().nextInt(0, 5)];
        var obj = objectClass
            .getConstructor(String.class, int.class)
            .newInstance(name, age);
        if (isValid(obj)) {
            return obj;
        }
        return nextObject(obj.getClass());
    }

    private boolean isValid(Object object) {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            var validator = factory.getValidator();
            Set<ConstraintViolation<Object>> constraintViolations =
                validator.validate(object);
            return constraintViolations.isEmpty();
        }
    }

}
