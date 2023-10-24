package edu.hw_2.task2_tests;

import edu.hw_2.task2.variant4.Rectangle;
import edu.hw_2.task2.variant4.Square;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class Variant4Test {
    static Arguments[] rectangles() {
        return new Arguments[] {
            Arguments.of(new Rectangle(20, 5)),
            Arguments.of(new Square(10, 10))
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
            Rectangle rect = new Square(10, 10);
            rect.setWidth(20);
            rect.setHeight(5);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rectangleException() {
        assertThatThrownBy(() -> {
            Rectangle rect = new Square(20, 5);
            rect.setWidth(5);
        }).isInstanceOf(IllegalArgumentException.class);
    }
}

