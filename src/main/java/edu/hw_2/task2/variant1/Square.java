package edu.hw_2.task2.variant1;

public class Square extends Rectangle {

    private final IllegalArgumentException warning = new IllegalArgumentException(
        "This is a square and sides must be equal"
    );

    @Override public Rectangle setWidth(int width) {
        return super.setWidth(width);
    }

    @Override
    public Rectangle setHeight(int height) {
        return super.setHeight(height);
    }

    @Override
    public Rectangle setHeightAndWidth(int height, int width) {
        if (height == width) {
            return super.setHeightAndWidth(height, width);
        } else {
            throw warning;
        }
    }
}
