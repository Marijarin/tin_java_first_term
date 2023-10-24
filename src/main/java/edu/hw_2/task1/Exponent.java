package edu.hw_2.task1;

public record Exponent(Expr e, int c) implements Expr {
    private Constant convert(int c) {
        return new Constant(c);
    }

    @Override
    public double evaluate() {
        return Math.pow(e.evaluate(), convert(c).evaluate());
    }
}
