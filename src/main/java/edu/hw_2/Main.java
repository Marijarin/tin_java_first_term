package edu.hw_2;

import edu.hw_2.task1.Expr.Addition;
import edu.hw_2.task1.Expr.Constant;
import edu.hw_2.task1.Expr.Exponent;
import edu.hw_2.task1.Expr.Multiplication;
import edu.hw_2.task1.Expr.Negate;
import edu.hw_2.task3.ConnectionManager;
import edu.hw_2.task3.DefaultConnectionManager;
import edu.hw_2.task3.FaultyConnectionManager;
import edu.hw_2.task3.PopularCommandExecutor;
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

    @SuppressWarnings("MagicNumber")
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

        ConnectionManager m1 = new DefaultConnectionManager();
        ConnectionManager m2 = new FaultyConnectionManager();
        int maxAttempts = 5;
        PopularCommandExecutor p1 = new PopularCommandExecutor(m1, maxAttempts);
        PopularCommandExecutor p2 = new PopularCommandExecutor(m2, maxAttempts);
        //p1.updatePackages();
        p2.updatePackages();
    }

    public static CallingInfo call() {
        return CallingInfo.callingInfo();
    }


}
