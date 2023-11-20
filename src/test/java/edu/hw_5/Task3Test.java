package edu.hw_5;

import edu.hw_5.task_3.AgoDateParser;
import edu.hw_5.task_3.CommonDateParser;
import edu.hw_5.task_3.DateFormatUtil;
import edu.hw_5.task_3.WordsDateParser;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @Test
    void commonDateParserSuitableInputCheck() {
        CommonDateParser commonDateParser = new CommonDateParser();
        String toCheck = "2023-8-9";

        var result = commonDateParser.check(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2023, 8, 9)));
    }

    @Test
    void commonDateParserSuitableInputParsedDash() {
        CommonDateParser cdp = new CommonDateParser();
        String toCheck = "2023-8-9";

        var result = cdp.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2023, 8, 9)));
    }

    @Test
    void commonDateParserSuitableInputParsedSlash() {
        CommonDateParser commonDateParser = new CommonDateParser();
        String toCheck = "9/8/23";

        var result = commonDateParser.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2023, 8, 9)));
    }

    @Test
    void commonDateParserBadInputParsed() {
        CommonDateParser commonDateParser = new CommonDateParser();
        String toCheck = "19/18/23";

        var result = commonDateParser.process(toCheck);
        assertThat(result).isEmpty();
    }

    @Test
    void agoDateParserSuitableInputCheck() {
        AgoDateParser agoDateParser = new AgoDateParser();
        String toCheck = "10 days ago";

        var result = agoDateParser.check(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void agoDateParserSuitableInputParsed() {
        AgoDateParser agoDateParser = new AgoDateParser();
        String toCheck = "10 days ago";

        var result = agoDateParser.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void agoDateParserBadInput() {
        AgoDateParser agoDateParser = new AgoDateParser();
        String toCheck = "ten days ago";

        var result = agoDateParser.process(toCheck);

        assertThat(result).isEmpty();
    }

    @Test
    void wordsDateParserSuitableInputCheck() {
        WordsDateParser wordsDateParser = new WordsDateParser();
        String toCheck = "today";

        var result = wordsDateParser.check(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now()));
    }

    @Test
    void wordsDateParserSuitableInputParsed() {
        WordsDateParser wordsDateParser = new WordsDateParser();
        String toCheck = "tomorrow";

        var result = wordsDateParser.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().plusDays(1)));
    }

    @Test
    void wordsDateParserBadInput() {
        WordsDateParser wordsDateParser = new WordsDateParser();
        String toCheck = "last week";

        var result = wordsDateParser.process(toCheck);

        assertThat(result).isEmpty();
    }

    @Test
    void dateFormatUtilAgoParser() {
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        String toCheck = "10 days ago";

        var result = dateFormatUtil.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void dateFormatUtilWordsParser() {
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        String toCheck = "yesterday";

        var result = dateFormatUtil.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
    }

    @Test
    void dateFormatUtilCommonParser() {
        DateFormatUtil dateFormatUtil = new DateFormatUtil();
        String toCheck = "1023-08-8";

        var result = dateFormatUtil.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(1023, 8, 8)));
    }
}
