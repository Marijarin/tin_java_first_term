package edu.hw_2.task2.variant1;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle setWidth(int width) {
        this.width = width;
        return this;
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return this;
    }

    public Rectangle setHeightAndWidth(int height, int width) {
        this.height = height;
        this.width = width;
        return this;
    }

    public double area() {
        return width * height;
    }
}
