package edu.hw_2.task2_tests;

import edu.hw_2.task2.variant3.Rectangle;
import edu.hw_2.task2.variant3.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Variant3Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(20, 5)),
            Arguments.of(new Square(10)),
            Arguments.of(new Rectangle(10, 10)),
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        assertThat(rect.area()).isEqualTo(100.0);
    }
}

