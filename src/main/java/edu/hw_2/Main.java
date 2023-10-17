package edu.hw_2;

import edu.hw_2.task1.Expr.*;
import edu.hw_2.task3.StableConnection;
import edu.hw_2.task4.CallingInfo;
import edu.hw_2.task4.ClassToCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("Magic number")
    public static void main(String[] args) throws Exception {
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var mult = new Multiplication(sumTwoFour, negOne);
        var exp = new Exponent(mult, 2);
        var res = new Addition(exp, new Constant(1));

        LOGGER.info(res + " = " + res.evaluate());
        LOGGER.info(CallingInfo.callingInfo());
        ClassToCall c = new ClassToCall();
        LOGGER.info(c.call());
        LOGGER.info(call());
    }
    public static CallingInfo call() throws Exception {
        return CallingInfo.callingInfo();
    }
}
