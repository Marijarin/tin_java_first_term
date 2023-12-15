package edu.hw_10;

import edu.hw_10.task2.CacheProxy;
import edu.hw_10.task2.Calculator;
import edu.hw_10.task2.FibCalculator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws FileNotFoundException {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @Test
    void savesCalculationsToCache()
        throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Calculator calculator = new FibCalculator();
        Object proxy = CacheProxy
            .create(Calculator.class, calculator);
        for (int i = 0; i < 2; i++) {
            proxy.getClass().getMethod("fi", int.class)
                .invoke(proxy, 5);
        }
        assertThat(outputStreamCaptor.toString().trim()).contains("5");
    }

    @Test
    void savesCalculationsToDisk()
        throws IOException {
        String fileName = "src/test/java/edu/hw_10/diskMap.txt";
        FibCalculator fibCalculator = new FibCalculator();
        Calculator proxy = CacheProxy
            .create(Calculator.class, fibCalculator);
        proxy.fib(5);
        assertThat(Files.readAllLines(Path.of(fileName)).getFirst()).isEqualTo("5");
    }

    @AfterEach
    public void tearDown() {
        System.setOut(standardOut);
    }
}
