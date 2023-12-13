package edu.hw_11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.MethodDelegation;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void multipliesInsteadOfAdding()
        throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        int result = new ByteBuddy()
            .subclass(ArithmeticUtils.class)
            .method(named("sum")).intercept(MethodDelegation.to(Multiplier.class))
            .make()
            .load(getClass().getClassLoader())
            .getLoaded()
            .getConstructor()
            .newInstance()
            .sum(1,2);

        assertThat(result).isEqualTo(2);
    }
}
