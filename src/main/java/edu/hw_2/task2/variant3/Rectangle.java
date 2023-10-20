package edu.hw_2.task2.variant3;

public class Rectangle {
    private final int width;
    private final int height;

    public Rectangle(int width, int height) {
        this.height = height;
        this.width = width;
    }

    public double area() {
        return width * height;
    }
}
