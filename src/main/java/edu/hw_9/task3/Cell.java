package edu.hw_9.task3;

public record Cell(int y, int x) {
    @Override
    public String toString() {
        return ">>cell: " + this.y() + "," + this.x() + ">> ";
    }
}
