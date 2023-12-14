package edu.hw_11;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.jar.asm.Opcodes;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import java.lang.reflect.InvocationTargetException;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void multipliesInsteadOfAdding() {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw_11.ArithmeticUtils")
            .resolve();
        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(named("sum")).intercept(MethodDelegation.to(Multiplier.class))
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);

        int result = ArithmeticUtils
            .sum(1, 2);

        assertThat(result).isEqualTo(2);
    }
}
