package edu.hw_2.task1;


public record Constant(int number) implements Expr {
    @Override
    public double evaluate() {
        return number;
    }
}
