package edu.hw_6;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings({"MultipleStringLiterals", "MagicNumber"})
public class DiskMap implements Map<String, String> {
    public final String fileName = System.getenv("HOME") + "/diskMap.txt";
    Map<String, String> inMemoryStorage = new HashMap<>();
    String key = "";
    String value;

    //#1
    public void readToRuntime() {
        try (FileChannel inChannel = FileChannel.open(Path.of(fileName))) {
            ByteBuffer byteBuffer = ByteBuffer.allocate(64);
            StringBuilder sb = new StringBuilder();
            int bytesRead = inChannel.read(byteBuffer);
            while (bytesRead != -1) {
                byteBuffer.flip();
                while (byteBuffer.hasRemaining()) {
                    char c = (char) byteBuffer.get();
                    sb.append(buildingFromBuffer(sb, c));
                }
                byteBuffer.clear();
                bytesRead = inChannel.read(byteBuffer);
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    private String buildingFromBuffer(StringBuilder sb, char c) {
        switch (c) {
            case ':' -> {
                key = sb.toString();
                sb.delete(0, sb.length());
            }
            case '\n' -> {
                value = sb.toString();
                sb.delete(0, sb.length());
                this.put(key, value);
            }
            default -> sb.append(c);
        }
        return sb.toString();
    }

    public void writeToFile() {
        try (RandomAccessFile writer = new RandomAccessFile(fileName, "rw");
             FileChannel channel = writer.getChannel()) {
            if (!inMemoryStorage.isEmpty()) {
                for (Map.Entry<String, String> e : inMemoryStorage.entrySet()) {
                    String oneSet = e.getKey() + ":" + e.getValue() + "\n";
                    byte[] bytes = oneSet.getBytes();
                    ByteBuffer byteBuffer = ByteBuffer.allocate(oneSet.length());
                    byteBuffer.put(bytes);
                    byteBuffer.flip();
                    channel.write(byteBuffer);
                    byteBuffer.clear();
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

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

    //#2
    public void cloneFile(Path path) {
        int copyNumber = 1;
        StringBuilder sb = new StringBuilder(path.toString());
        StringBuilder end = new StringBuilder();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.') {
                end.append(sb.toString(), i, sb.length());
                sb.delete(i, sb.length());
            }
        }
        Path target = Path.of(sb + " - копия" + end);
        Path targetNext = Path.of(sb + " - копия" + "(" + copyNumber + ")" + end);
        if (Files.exists(path) && !Files.exists(target)) {
            tryCopy(path, target);
        } else if (Files.exists(target) && !Files.exists(targetNext)) {
            tryCopy(path, targetNext);
        } else if (Files.exists(targetNext)) {
            try (Stream<Path> pathStream = Files.find(Path.of(System.getenv("HOME")), 1, (p, basicFileAttributes) -> {
                File file = p.toFile();
                return !file.isDirectory()
                    && file.getName().contains("копия");
            }).sorted()) {
                List<Path> paths = pathStream.toList();
                Path last = paths.get(paths.size() - 2);
                copyNumber = Integer.parseInt(last.toString()
                    .split("\\(")[1]
                    .split("\\)")[0]);
                copyNumber++;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            targetNext = Path.of(sb + " - копия" + "(" + copyNumber + ")" + end);
            tryCopy(path, targetNext);
        }
    }

    private void tryCopy(Path path, Path target) {
        try {
            Files.copy(path, target);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
