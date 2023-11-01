package edu.project_2.demo;

public record Cell(int row, int col, Type type) {
    public enum Type { WALL, PASSAGE }
}
