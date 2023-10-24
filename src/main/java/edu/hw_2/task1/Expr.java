package edu.hw_2.task1;


    sealed public interface Expr permits Addition, Constant, Exponent, Multiplication, Negate {
        double evaluate();
    }
