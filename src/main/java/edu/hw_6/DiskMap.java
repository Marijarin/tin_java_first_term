package edu.hw_6;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DiskMap implements Map<String, String> {
    Map<String, String> inMemoryStorage = new HashMap<>();

    @Override
    public int size() {
        return this.inMemoryStorage.size();
    }

    @Override
    public boolean isEmpty() {
        return inMemoryStorage.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return inMemoryStorage.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return inMemoryStorage.containsValue(value);
    }

    @Override
    public String get(Object key) {
        return inMemoryStorage.get(key);
    }

    @Nullable
    @Override
    public String put(String key, String value) {
        return inMemoryStorage.put(key, value);
    }

    @Override
    public String remove(Object key) {
        return inMemoryStorage.remove(key);
    }

    @Override
    public void putAll(@NotNull Map<? extends String, ? extends String> m) {
        inMemoryStorage.putAll(m);
    }

    @Override
    public void clear() {
        inMemoryStorage.clear();
    }

    @NotNull
    @Override
    public Set<String> keySet() {
        return inMemoryStorage.keySet();
    }

    @NotNull
    @Override
    public Collection<String> values() {
        return inMemoryStorage.values();
    }

    @NotNull
    @Override
    public Set<Entry<String, String>> entrySet() {
        return inMemoryStorage.entrySet();
    }
}
