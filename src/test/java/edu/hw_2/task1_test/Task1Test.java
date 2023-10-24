package edu.hw_2.task1_test;

import edu.hw_2.task1.Addition;
import edu.hw_2.task1.Constant;
import edu.hw_2.task1.Exponent;
import edu.hw_2.task1.Expr;
import edu.hw_2.task1.Multiplication;
import edu.hw_2.task1.Negate;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task1Test {
    Expr two = new Constant(2);
    Expr four = new Constant(4);
    Expr negOne = new Negate(new Constant(1));
    Expr sumTwoFour = new Addition(two, four);
    Expr mult = new Multiplication(sumTwoFour, negOne);

    @Test
    void evaluateConst() {
        double result = two.evaluate();

        assertThat(result).isEqualTo(2.0);
    }

    @Test
    void evaluateNeg() {
        double result = negOne.evaluate();

        assertThat(result).isEqualTo(-1.0);
    }

    @Test
    void evaluateNegFromNeg() {
        Expr negOne = new Negate(new Constant(-1));
        double result = negOne.evaluate();

        assertThat(result).isEqualTo(1.0);
    }

    @Test
    void evaluateAddition() {
        double result = sumTwoFour.evaluate();

        assertThat(result).isEqualTo(6.0);
    }

    @Test
    void evaluateMultiplication() {
        double result = mult.evaluate();

        assertThat(result).isEqualTo(-6.0);
    }

    @Test
    void evaluateExponent() {
        Expr exp = new Exponent(mult, 2);
        double result = exp.evaluate();

        assertThat(result).isEqualTo(36.0);
    }
}
