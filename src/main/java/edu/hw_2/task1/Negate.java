package edu.hw_2.task1;

public record Negate(Constant constant) implements Expr {
    @Override
    public double evaluate() {
        if (constant.number() > 0) {
            return -constant.number();
        } else {
            return Math.abs(constant.number());
        }
    }
}
