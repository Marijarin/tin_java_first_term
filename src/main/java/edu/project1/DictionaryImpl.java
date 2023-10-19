package edu.project1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import static java.util.Map.entry;

public class DictionaryImpl implements edu.project1.Dictionary {
    private final Map<String, Integer> words = Map.ofEntries(
        entry("scratch", 2),
        entry("zipper", 2),
        entry("kilobyte", 2),
        entry("jelly", 2),
        entry("peekaboo", 2),
        entry("ivy", 1),
        entry("no", 1),
        entry("name", 1),
        entry("me", 1),
        entry("cat", 1),
        entry("dog", 1),
        entry("I", 1)
    );

    List<String> getWords(int level) {
        List<String> selectedWords = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            if (entry.getValue() == level && entry.getKey().length() > 1) {
                selectedWords.add(entry.getKey());
            }
        }
        return selectedWords;
    }

    public @NotNull String randomWord(int level) {
        List<String> selectedWords = getWords(level);
        int random = (int) (Math.random() * selectedWords.size());
        return selectedWords.get(random);
    }
}
