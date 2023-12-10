package edu.hw_9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class ManyFilesDirFinder extends RecursiveTask<List<Path>> {
    protected final Path path;
    protected final int loLimit;

    public ManyFilesDirFinder(Path path, int loLimit) {
        this.path = path;
        this.loLimit = loLimit;
    }

    @Override
    protected List<Path> compute() {
        List<Path> results = new ArrayList<>();
        if (path.toFile().isDirectory()) {
            List<ForkJoinTask<List<Path>>> forks = new ArrayList<>();
            for (File file : Objects.requireNonNull(path.toFile().listFiles())) {
                forks.add(new ManyFilesDirFinder(
                    file.toPath(),
                    loLimit
                ).fork());
            }
            for (var task : forks) {
                results.addAll(task.join());
            }
            if (Objects.requireNonNull(path.toFile().listFiles()).length >= loLimit) {
                results.add(path);
            }
        }
        return results;
    }

}
