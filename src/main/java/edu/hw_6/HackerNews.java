package edu.hw_6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.regex.Pattern;
import static java.net.http.HttpClient.newHttpClient;

@SuppressWarnings({"MagicNumber", "LineLength"})
public class HackerNews {
    String body = "";

    long[] hackerNewsTopStories() {
        var request = HttpRequest.newBuilder()
            .uri(URI.create("https://hacker-news.firebaseio.com/v0/topstories.json"))
            .GET()
            .timeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();
        try (var client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();
            var longList = idsFromResponse(body);
            var result = new long[longList.length];
            for (int i = 0; i < longList.length; i++) {
                result[i] = longList[i];
            }
            return result;
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    private Long[] idsFromResponse(String responseBody) {
        return responseBody.lines().filter(line -> line.equals("[") || line.equals("]")).map(
            it -> Long.parseLong(it.split(",")[0])
        ).toList().toArray(new Long[0]);
    }

    @SuppressWarnings("RegexpSingleLine")
    String news(long id) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
            .GET()
            .timeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();
        var pattern = Pattern.compile(
            "^(\\{[\\s\\S]*\\].\\n)(..\\\".+\\\"..\\d+\\,\\n)(..\\\".+\\\"..\\d+\\,\\n)(..\\\"\\w+\\\"..\\\"(?<title>(.+)\\\"\\,\\n))(..\\\"\\w+\\\"..\\\"(.+)\\\"\\,\\n)(.+)\\n");
        try (var client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();
            String title = "";
            var matcher = pattern.matcher(body);
            if (matcher.matches()) {
                title = matcher.group("title");
            }
            return title;
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }
}
