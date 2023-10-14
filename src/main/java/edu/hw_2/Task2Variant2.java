package edu.hw_2;

public interface Task2Variant2 {
    class Rectangle {
        private int width;
        private int height;

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public double area() {
            return width * height;
        }
    }

    class Square extends Rectangle {
        private int side = 0;

        private final IllegalArgumentException warning = new IllegalArgumentException("This is a square and sides have been set");

        @Override public void setWidth(int width) {
            if (this.side == 0) {
                this.side = width;
                super.setWidth(side);
                super.setHeight(side);
            } else {
                throw warning;
            }
        }

        @Override
        public void setHeight(int height) {
            if (this.side == 0) {
                this.side = height;
                super.setWidth(side);
                super.setHeight(side);
            } else {
                throw warning;
            }
        }
    }
}
