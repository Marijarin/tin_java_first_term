package edu.hw_6;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import static edu.hw_6.AbstractFilter.globMatches;
import static edu.hw_6.AbstractFilter.isReadable;
import static edu.hw_6.AbstractFilter.isWritable;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSingleLineJava"})
    public static void main(String[] args) throws IOException {

        QuotationsWriter quotationsWriter = new QuotationsWriter();
        quotationsWriter.writeToFile(quotationsWriter.quotation);
    }

    AbstractFilter filter = isReadable().and(isWritable()).and(globMatches("*.txt"));

}
