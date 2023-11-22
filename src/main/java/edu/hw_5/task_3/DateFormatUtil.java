package edu.hw_5.task_3;

import java.time.LocalDate;
import java.util.Optional;

public class DateFormatUtil {
    DateParser words = new WordsDateParser();
    DateParser common = new CommonDateParser();
    DateParser ago = new AgoDateParser();

    DateParser parser = DateParser.link(common, ago, words);

    public Optional<LocalDate> parseDate(String toParse) {
        return parser.check(toParse);
    }
}
