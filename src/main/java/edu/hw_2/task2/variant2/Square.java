package edu.hw_2.task2.variant2;

public class Square extends Rectangle {
    private int side;

    private final IllegalArgumentException warning = new IllegalArgumentException(
        "This is a square and sides must be equal"
    );

    @Override public void setWidth(int width) {
        if (this.side == 0 || this.side == width) {
            this.side = width;
            super.setWidth(side);
            super.setHeight(side);
        } else {
            throw warning;
        }
    }

    @Override
    public void setHeight(int height) {
        if (this.side == 0 || this.side == height) {
            this.side = height;
            super.setWidth(side);
            super.setHeight(side);
        } else {
            throw warning;
        }
    }
}
