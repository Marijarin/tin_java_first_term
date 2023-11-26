package edu.hw_8;

import edu.hw_8.Task3.DecoderThreadsStats;
import edu.hw_8.Task3.MD5Decoder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public final class Main {
    private final static Logger LOGGER = LogManager.getLogger();

    private Main() {
    }

    @SuppressWarnings({"MagicNumber", "RegexpSinglelineJava"})
    public static void main(String[] args) {
        MD5Decoder md5Decoder = new MD5Decoder();
        md5Decoder.nextPassword();
        LOGGER.info(md5Decoder.deciphered);
        DecoderThreadsStats decoderThreadsStats = new DecoderThreadsStats();
        System.out.println(decoderThreadsStats.showThreadsStats());
    }
}
