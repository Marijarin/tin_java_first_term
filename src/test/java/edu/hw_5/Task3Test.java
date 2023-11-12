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
        CommonDateParser cdp = new CommonDateParser();
        String toCheck = "2023-8-9";

        var result = cdp.check(toCheck);

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
        CommonDateParser cdp = new CommonDateParser();
        String toCheck = "9/8/23";

        var result = cdp.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(2023, 8, 9)));
    }

    @Test
    void commonDateParserBadInputParsed() {
        CommonDateParser cdp = new CommonDateParser();
        String toCheck = "19/18/23";

        var result = cdp.process(toCheck);
        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    void agoDateParserSuitableInputCheck() {
        AgoDateParser adp = new AgoDateParser();
        String toCheck = "10 days ago";

        var result = adp.check(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void agoDateParserSuitableInputParsed() {
        AgoDateParser adp = new AgoDateParser();
        String toCheck = "10 days ago";

        var result = adp.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void agoDateParserBadInput() {
        AgoDateParser adp = new AgoDateParser();
        String toCheck = "ten days ago";

        var result = adp.process(toCheck);

        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    void wordsDateParserSuitableInputCheck() {
        WordsDateParser wdp = new WordsDateParser();
        String toCheck = "today";

        var result = wdp.check(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now()));
    }

    @Test
    void wordsDateParserSuitableInputParsed() {
        WordsDateParser wdp = new WordsDateParser();
        String toCheck = "tomorrow";

        var result = wdp.process(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().plusDays(1)));
    }

    @Test
    void wordsDateParserBadInput() {
        WordsDateParser wdp = new WordsDateParser();
        String toCheck = "last week";

        var result = wdp.process(toCheck);

        assertThat(result).isEqualTo(Optional.empty());
    }

    @Test
    void dateFormatUtilAgoParser() {
        DateFormatUtil d = new DateFormatUtil();
        String toCheck = "10 days ago";

        var result = d.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(10)));
    }

    @Test
    void dateFormatUtilWordsParser() {
        DateFormatUtil d = new DateFormatUtil();
        String toCheck = "yesterday";

        var result = d.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.now().minusDays(1)));
    }

    @Test
    void dateFormatUtilCommonParser() {
        DateFormatUtil d = new DateFormatUtil();
        String toCheck = "1023-08-8";

        var result = d.parseDate(toCheck);

        assertThat(result).isEqualTo(Optional.of(LocalDate.of(1023, 8, 8)));
    }
}
