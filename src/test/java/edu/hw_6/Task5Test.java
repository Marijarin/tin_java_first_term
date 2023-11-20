package edu.hw_6;

import org.junit.jupiter.api.Test;
import java.net.URI;
import java.net.http.HttpRequest;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class Task5Test {
    HackerNews hackerNews = new HackerNews();

    @Test
    void containsParticularId() {
        long[] allStories = hackerNews.hackerNewsTopStories();

        String firstStory = hackerNews.body.substring(1, hackerNews.body.length() - 1).split(",")[0];
        long oneStory = Long.parseLong(firstStory);

        assertThat(allStories[0]).isEqualTo(oneStory);
    }

    @Test
    void showsStoryNameOfParticularId() {
        long id = 37570037;
        String newsOfId = hackerNews.news(id);

        assertThat(newsOfId).isEqualTo("JDK 21 Release Notes");
    }
}
