package edu.hw_2.task2.variant4;

public class Rectangle {
    private int width;
    private int height;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public Rectangle setWidth(int width) {
        this.width = width;
        return new Rectangle(width, height);
    }

    public Rectangle setHeight(int height) {
        this.height = height;
        return new Rectangle(width, height);
    }

    public double area() {
        return width * height;
    }
}

