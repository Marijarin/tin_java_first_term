package edu.hw_2.task1;


    sealed public interface Expr {
        double evaluate();

        record Constant(int number) implements Expr {
            @Override
            public double evaluate() {
                return number;
            }
        }

        record Negate(Constant constant) implements Expr {
            @Override
            public double evaluate() {
                if (constant.number > 0) {
                    return -constant.number();
                } else {
                    return Math.abs(constant.number);
                }
            }
        }

        record Exponent(Expr e, int c) implements Expr {
            private Constant convert(int c) {
                return new Constant(c);
            }

            @Override
            public double evaluate() {
                return Math.pow(e.evaluate(), convert(c).evaluate());
            }
        }

        record Addition(Expr e1, Expr e2) implements Expr {
            @Override
            public double evaluate() {
                return (e1.evaluate() + e2.evaluate());
            }
        }

        record Multiplication(Expr e1, Expr e2) implements Expr {
            @Override
            public double evaluate() {
                return e1.evaluate() * e2.evaluate();
            }
        }
    }
