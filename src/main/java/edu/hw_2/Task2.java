package edu.hw_2;

public interface Task2 {
    class Rectangle {
        private int width;
        private int height;

        public Rectangle setWidth(int width) {
            if (this.height == 0) {
                this.height = width;
            }
            this.width = width;
            return this;
        }

        public Rectangle setHeight(int height) {
            if (this.width == 0) {
                this.width = height;
            }
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

    class Square extends Rectangle {

        private final IllegalArgumentException warning = new IllegalArgumentException("This is a square and sides must be equal");

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
}
