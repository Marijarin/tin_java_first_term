package edu.hw_2;

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
    public static void main(String[] args) {

        LOGGER.info(CallingInfo.callingInfo());
        ClassToCall c = new ClassToCall();
        LOGGER.info(c.callCallingInfo());
        LOGGER.info(callFromMain());

        ConnectionManager md = new DefaultConnectionManager();
        ConnectionManager mf = new FaultyConnectionManager();
        int maxAttempts = 5;
        PopularCommandExecutor p1 = new PopularCommandExecutor(md, maxAttempts);
        PopularCommandExecutor p2 = new PopularCommandExecutor(mf, maxAttempts);
        p1.updatePackages();
        p2.updatePackages();
    }

    public static CallingInfo callFromMain() {
        return CallingInfo.callingInfo();
    }


}
