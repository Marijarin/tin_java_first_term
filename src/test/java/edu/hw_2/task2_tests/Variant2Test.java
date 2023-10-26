package edu.hw_2.task2_tests;

import edu.hw_2.task2.variant2.Rectangle;
import edu.hw_2.task2.variant2.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Variant2Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle()),
            Arguments.of(new Square())
        };
    }

    @ParameterizedTest
    @MethodSource("rectangles")
    void rectangleArea(Rectangle rect) {
        rect.setWidth(10);
        rect.setHeight(10);

        assertThat(rect.area()).isEqualTo(100.0);
    }

    @Test
    void rectangleAreaException() {
        assertThatThrownBy(() -> {
            Rectangle rect = new Square();
            rect.setWidth(20);
            rect.setHeight(5);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

