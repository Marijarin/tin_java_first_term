package edu.hw_9.task2;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class PredicateFilesFinder extends RecursiveTask<List<Path>> {
    protected final Path path;
    protected final Conditioner conditioner;

    public PredicateFilesFinder(Path path, Conditioner conditioner) {
        this.path = path;
        this.conditioner = conditioner;
    }

    @Override
    protected List<Path> compute() {
        List<Path> results = new ArrayList<>();
        if (conditioner.isCondition(path.toFile())) {
            results.add(path);
        }
        if (path.toFile().isDirectory()) {
            List<ForkJoinTask<List<Path>>> forks = new ArrayList<>();
            for (File file : Objects.requireNonNull(path.toFile().listFiles())) {
                forks.add(new PredicateFilesFinder(
                    file.toPath(),
                    this.conditioner
                ).fork());
            }
            for (var task : forks) {
                results.addAll(task.join());
            }
        }
        return results;
    }

}
