package edu.hw_6;

import edu.hw_2.task3.ConnectionManager;
import edu.hw_2.task3.DefaultConnectionManager;
import edu.hw_2.task3.FaultyConnectionManager;
import edu.hw_2.task3.PopularCommandExecutor;
import edu.hw_2.task4.CallingInfo;
import edu.hw_2.task4.ClassToCall;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.nio.file.Path;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) throws IOException {

        DiskMap dm = new DiskMap();
        dm.put("test1", "test1 value");
        dm.put("test2", "test2 value");
        LOGGER.info(dm.readToRuntime());
        dm.writeToFile();
        dm.cloneFile(Path.of(dm.fileName));
        dm.cloneFile(Path.of(dm.fileName));
        dm.cloneFile(Path.of(dm.fileName));
        dm.cloneFile(Path.of(dm.fileName));
    }



}
