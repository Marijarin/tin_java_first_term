package edu.hw_2.task2_tests;

import edu.hw_2.task2.variant1.Rectangle;
import edu.hw_2.task2.variant1.Square;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Variant1Test {
    static Arguments[] rectangles() {
        return new Arguments[]{
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect.setWidth(20);
        rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(200.0);
    }
}

