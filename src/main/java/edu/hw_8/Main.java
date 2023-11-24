package edu.hw_8;

import edu.hw_8.Task3.MD5Decipherator;
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
        MD5Decipherator md5Decipherator = new MD5Decipherator();
        md5Decipherator.nextPassword();
        LOGGER.info(md5Decipherator.deciphered);
    }
}
