package edu.hw_2.task2.variant4;

public class Square extends Rectangle {
    private final int width;
    private final int height;
    private final IllegalArgumentException warning = new IllegalArgumentException(
        "This is a square and sides must be equal"
    );

    public Square(int width1, int height1) {
        super(width1, height1);
        this.width = width1;
        this.height = height1;
        if (width != height) {
            throw warning;
        }
    }

    @Override public Rectangle setWidth(int width) {
        if (width != this.height) {
            throw warning;
        }
        return super.setWidth(width);
    }

    @Override public Rectangle setHeight(int height) {
        if (height != this.width) {
            throw warning;
        }
        return super.setHeight(height);
    }
}
