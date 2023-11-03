package edu.project_2;

public record Coordinate(int x, int y, Coordinate parent) {
    public Coordinate(int x, int y) {
        this(x, y, null);
    }
    public Coordinate(int x, int y, Coordinate parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

}
