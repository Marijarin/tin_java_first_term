package edu.hw_11;

import edu.hw_11.task3.FibImplementation;
import java.lang.reflect.InvocationTargetException;
import net.bytebuddy.ByteBuddy;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.ClassFileLocator;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.pool.TypePool;
import org.junit.jupiter.api.Test;
import static net.bytebuddy.matcher.ElementMatchers.named;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void countsFibGenerated() {
        TypeDescription typeDescription = TypePool.Default.ofSystemLoader()
            .describe("edu.hw_11.FibExample")
            .resolve();


        new ByteBuddy()
            .redefine(typeDescription, ClassFileLocator.ForClassLoader.ofSystemLoader())
            .method(named("fib")).intercept(FibImplementation.INSTANCE)
            .make()
            .load(ClassLoader.getSystemClassLoader(), ClassLoadingStrategy.Default.INJECTION);

            assertThat(FibExample.fib(7)).isEqualTo(13L);

    }
}
