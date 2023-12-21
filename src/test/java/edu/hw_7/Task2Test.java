package edu.hw_7;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task2Test {
    @Test
    void twoStreamsGiveSameResult(){
        FactorialCounter factorialCounter = new FactorialCounter(1000000);
        var resultPar = factorialCounter.countFactorialInParallel();
        var resultNotPar = factorialCounter.countFactorial();

       assertThat(resultPar).isEqualTo(resultNotPar);
    }
}
