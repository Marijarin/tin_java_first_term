package edu.hw_11;

import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    @Test
    void generateClassThatPrintsHello()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String hello = new ByteBuddy()
            .subclass(Object.class)
            .name("example.Type")
            .method(named("toString")).intercept(FixedValue.value("Hello ByteBuddy!"))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .getConstructor()
            .newInstance()
            .toString();

        assertThat(hello).isEqualTo("Hello ByteBuddy!");
    }
}
