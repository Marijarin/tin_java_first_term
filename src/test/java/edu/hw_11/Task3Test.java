package edu.hw_11;

import edu.hw_11.task3.FibExample;
import edu.hw_11.task3.FibImplementation;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void countsFibGenerated()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, InstantiationException {
        assertThat(new ByteBuddy()
            .subclass(FibExample.class)
            .method(named("fib")).intercept(FibImplementation.INSTANCE)
            .make()
            .load(getClass().getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
            .getLoaded()
            .getDeclaredConstructor()
            .newInstance()
            .fib(5)).isEqualTo(6);

    }
}
