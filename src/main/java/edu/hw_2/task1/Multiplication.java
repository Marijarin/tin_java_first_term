package edu.hw_2.task1;

public record Multiplication(Expr e1, Expr e2) implements Expr {
    @Override
    public double evaluate() {
        return e1.evaluate() * e2.evaluate();
    }
}
