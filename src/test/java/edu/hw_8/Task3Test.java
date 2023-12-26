package edu.hw_8;

import edu.hw_8.Task3.DecoderThreadsStats;
import edu.hw_8.Task3.MD5Decoder;
import edu.hw_8.Task3.MD5HashMaker;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.assertj.core.data.Percentage;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task3Test {
    @RepeatedTest(10)
    void canGenerateNext() {
        MD5Decoder md5Decoder = new MD5Decoder();
        String nextPW = md5Decoder.generateNext();

        Pattern pattern = Pattern.compile("^[a-z]{4}$");
        Matcher matcher = pattern.matcher(nextPW);

        assertThat(matcher.matches()).isTrue();
    }

    @Test
    void canMakeMD5() {
        String pw = "ssss";
        String md5 = "8f60c8102d29fcd525162d02eed4566b";

        MD5HashMaker md5HashMaker = new MD5HashMaker();
        String result = md5HashMaker.makeMD5(pw);

        assertThat(result).isEqualTo(md5);
    }

    @Test
    void canGenerateStolenData() {
        MD5HashMaker md5HashMaker = new MD5HashMaker();
        String pw1 = "ssss";
        String pw2 = "aaaa";
        String md51 = "8f60c8102d29fcd525162d02eed4566b";
        String md52 = "74b87337454200d4d33f80c4663dc5e5";

        var mapStolen = md5HashMaker.generateStolenData(List.of(pw1, pw2), List.of("name1", "name2"));
        var correct = Map.of(
            md51, "name1",
            md52, "name2"
        );

        assertThat(mapStolen).isEqualTo(correct);
    }

    @Test
    void canDecodeStolenOneThread() {
        String pw1 = "ssss";
        String pw2 = "aaaa";
        String md51 = "8f60c8102d29fcd525162d02eed4566b";
        String md52 = "74b87337454200d4d33f80c4663dc5e5";

        MD5Decoder md5Decoder = new MD5Decoder();
        md5Decoder.stolen = new ConcurrentHashMap<>();
        md5Decoder.stolen.put(md51, "name1");
        md5Decoder.stolen.put(md52, "name2");

        md5Decoder.nextPassword();
        var result = md5Decoder.deciphered;

        var correct = Map.of(
            "name1", pw1,
            "name2", pw2
        );

        assertThat(result).isEqualTo(correct);
    }

    @Test
    void canDecodeStolenManyThreads() {
        String pw1 = "ssss";
        String pw2 = "aaaa";
        String md51 = "8f60c8102d29fcd525162d02eed4566b";
        String md52 = "74b87337454200d4d33f80c4663dc5e5";

        MD5Decoder md5Decoder = new MD5Decoder();
        md5Decoder.stolen = new ConcurrentHashMap<>();
        md5Decoder.stolen.put(md51, "name1");
        md5Decoder.stolen.put(md52, "name2");

        md5Decoder.nextPasswordManyThreads(3);
        var result = md5Decoder.deciphered;

        var correct = Map.of(
            "name1", pw1,
            "name2", pw2
        );

        assertThat(result).isEqualTo(correct);
    }

    @RepeatedTest(5)
    void manyThreadsFaster() {
        DecoderThreadsStats decoderThreadsStats = new DecoderThreadsStats();
        decoderThreadsStats.makeThreadsStats();

        var result = decoderThreadsStats.timeInS;

        long max = Arrays.stream(result).max().orElseThrow();

        assertThat(result[0]).isCloseTo(max, Percentage.withPercentage(60));
    }
}
