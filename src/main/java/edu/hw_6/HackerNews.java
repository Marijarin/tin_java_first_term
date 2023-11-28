package edu.hw_6;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
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
            return idsFromResponse(body);
        } catch (IOException | InterruptedException e) {
            return new long[0];
        }
    }

    private long[] idsFromResponse(String responseBody) {
        return Arrays.stream(responseBody.substring(1, responseBody.length() - 1)
                .split(","))
            .map(Long::parseLong)
            .mapToLong(Long::longValue)
            .toArray();
    }

    /**
     * In function under this comment two short regular expressions are used.
     * They cut response body in two separate strings each time, using json variable names which we know in advance.
     * These are "title" and "type".
     * This regular expression chain suits to take a value of variable title from json string.
     **/
    @SuppressWarnings("RegexpSingleLine")
    String news(long id) {
        var request = HttpRequest.newBuilder()
            .uri(URI.create(String.format("https://hacker-news.firebaseio.com/v0/item/%d.json", id)))
            .GET()
            .timeout(Duration.of(5, ChronoUnit.SECONDS))
            .build();
        try (var client = newHttpClient()) {
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            body = response.body();
            return body.split("title\":\"")[1].split("\",\"type")[0];
        } catch (IOException | InterruptedException e) {
            return "";
        }
    }
}
